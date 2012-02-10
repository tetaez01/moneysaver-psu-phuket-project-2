package edusoft.android.report;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import edusoft.android.database.DatabaseHelper;
import edusoft.android.main.R;
import edusoft.android.reuse.Utility;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReportActivity extends Activity {
	
	private static final String[] month = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	private static final String[] year = {new Utility().getCurrentYear(),Integer.toString(Calendar.getInstance().get(Calendar.YEAR)-1)};
	private static ArrayAdapter adapterCategory;
	private static List<PieDetailsItem> PieData ;
	boolean isCheck = true;
	
    double income;
    double expense;
    PieDetailsItem Item ;
	DatabaseHelper dbHelp;
	RadioButton radMonth;
	RadioButton radYear;
	Spinner spinnerCategory;
	ImageView imgGraph;
	TextView txtIncome;
	TextView txtExpense;
	TextView txtOverPaid;
		
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
	        setContentView(R.layout.report_main);
	        onCreateInitailLayout();
	        radMonth.setChecked(true);
	        radMonth.setOnClickListener(radMonthOnclick);
	
	        radYear.setChecked(false);
	        radYear.setOnClickListener(radYearOnclick);
	        
	        adapterCategory = new ArrayAdapter(this,android.R.layout.simple_spinner_item, month);
	        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinnerCategory.setAdapter(adapterCategory);
	        spinnerCategory.setSelection(Integer.parseInt(new Utility().getCurrentMonth()));
	        spinnerCategory.setOnItemSelectedListener(categorySpinner_listenner);	           
	        // ยัดรูปที่ได้จากการวาดมาแสดง
	        imgGraph.setBackgroundColor(0xff000000);     
	        imgGraph.setImageBitmap(getCirclePie(getReportDeatailWithMonth(Integer.parseInt(new Utility().getCurrentMonth()))));
        }catch (Exception e) {
        	AlertDialog.Builder b = new AlertDialog.Builder(ReportActivity.this);
			b.setMessage(e.toString());
			b.show();
		}

    }
  //  
  //======================================= initial layout ===========================================
  	private void onCreateInitailLayout()
  	{
  		radMonth = (RadioButton) findViewById(R.id.report_main_radMonth);
  		radYear = (RadioButton) findViewById(R.id.report_main_radYear);
  		spinnerCategory = (Spinner) findViewById(R.id.report_main_spinnerCategory);
  		imgGraph =  (ImageView) findViewById(R.id.report_main_imgGraph);
  		txtIncome  =  (TextView) findViewById(R.id.report_main_txtIncome);
  		txtExpense = (TextView) findViewById(R.id.report_main_txtExpense);
  		txtOverPaid = (TextView) findViewById(R.id.report_main_txtOver);
  		dbHelp = new DatabaseHelper(this);
  	}
  //======================================= Listenner ================================================
  	//การทำงานคลิ๊ก radMonth
	private OnClickListener radMonthOnclick = new OnClickListener() {
		public void onClick(View view) {		
			isCheck = true;
			circlePieChangeFromRadio(month,isCheck);
		}
	};
    //การทำงานคลิ๊ก radYear
  	private OnClickListener radYearOnclick = new OnClickListener() {
  		public void onClick(View view) {
  			isCheck = false;
  			circlePieChangeFromRadio(year,isCheck);
  		}
  	}; 	
  	// Spinner ของบัญชีที่เลือก
 	private AdapterView.OnItemSelectedListener categorySpinner_listenner = new AdapterView.OnItemSelectedListener() {
 		public void onItemSelected(AdapterView<?> parent,
 				View view, int pos, long id) {	
 			imgGraph.setBackgroundColor(0xff000000);
 			if(isCheck)
 				imgGraph.setImageBitmap(getCirclePie(getReportDeatailWithMonth(pos)));	
 			else
 				imgGraph.setImageBitmap(getCirclePie(getReportDeatailWithYear(Integer.parseInt(spinnerCategory.getSelectedItem().toString()))));	
 		}

 		public void onNothingSelected(AdapterView<?> parent) {
 		}
 	};
  //==================================== Method ที่เอาใว้ Reuse ภายใน Class ReportActivity =============================	
 	//เปลี่ยนกราฟหลังจากคลิ๊กที่  radMonth , radYear
 	public void circlePieChangeFromRadio(String[] radData,boolean isCheck)
 	{
		adapterCategory = new ArrayAdapter(this,android.R.layout.simple_spinner_item, radData);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);
        spinnerCategory.setOnItemSelectedListener(categorySpinner_listenner);
        if(isCheck){
        	spinnerCategory.setSelection(Integer.parseInt(new Utility().getCurrentMonth()));
        	// ยัดรูปที่ได้จากการวาดมาแสดง
            imgGraph.setBackgroundColor(0xff000000);     
            imgGraph.setImageBitmap(getCirclePie(getReportDeatailWithMonth(Integer.parseInt(new Utility().getCurrentMonth()))));
 		}else
 		{
 			spinnerCategory.setSelection(0);
 			// ยัดรูปที่ได้จากการวาดมาแสดง
 	        imgGraph.setBackgroundColor(0xff000000);     
 	        imgGraph.setImageBitmap(getCirclePie(getReportDeatailWithYear(Integer.parseInt(new Utility().getCurrentYear()))));
 		}	      
 	}
 	//นำข้อมูลจาก database ของรายรับ-รายจ่าย ในรายเดือน มาเพื่อยัดลงใน List เพื่อนำไปวาดเป็นกราฟต่อไป
 	public List<PieDetailsItem> getReportDeatailWithMonth(int month)
 	{
 		PieData = new ArrayList<PieDetailsItem>(0);
 		Item = new PieDetailsItem();;
        int MaxPieItems = 2;   
        //นำค่า income และ expense มาในส่ใน ArrayList เพื่อส่งไปวาดรูปกราฟอีกที
        for (int i = 0; i < MaxPieItems ; i++) {
        	Item       = new PieDetailsItem();
        	if(i==0){
        		income = Double.parseDouble(dbHelp.getIncomeWithMonth(Integer.toString(month+1)));
        		Item.Count = income;
        		Item.Color = Color.rgb(42, 127, 4);//0xff00ff00;
        	}else if(i == 1) {
        		expense  = Double.parseDouble(dbHelp.getExpenseWithMonth(Integer.toString(month+1)));
        		Item.Count = expense;
        		Item.Color = Color.rgb(250, 0, 0);//0xffff0000;
        	}
        	PieData.add(Item);
        }
        
        Item       = new PieDetailsItem();
        if((income - expense) <  0) 		Item.Count = expense - income;
        else 								Item.Count = 0;
        
        Item.Color = Color.rgb(255, 96, 0);
        PieData.add(Item);  

        return PieData;
 	}
 	//นำข้อมูลจาก database ของรายรับ-รายจ่าย ในรายเดือน มาเพื่อยัดลงใน List เพื่อนำไปวาดเป็นกราฟต่อไป
 	public List<PieDetailsItem> getReportDeatailWithYear(int year)
 	{
 		PieData = new ArrayList<PieDetailsItem>(0);
 		Item = new PieDetailsItem();;
        int MaxPieItems = 2;   
        //นำค่า income และ expense มาในส่ใน ArrayList เพื่อส่งไปวาดรูปกราฟอีกที
        for (int i = 0; i < MaxPieItems ; i++) {
        	Item       = new PieDetailsItem();
        	if(i==0){
        		income = Double.parseDouble(dbHelp.getIncomeWithYear(Integer.toString(year)));
        		Item.Count = income;
        		Item.Color = Color.rgb(42, 127, 4);//0xff00ff00;
        	}else if(i == 1) {
        		expense  = Double.parseDouble(dbHelp.getExpenseWithYear(Integer.toString(year)));
        		Item.Count = expense;
        		Item.Color = Color.rgb(250, 0, 0);//0xffff0000;
        	}
        	PieData.add(Item);
        }
        
        Item       = new PieDetailsItem();
        if((income - expense) <  0) 		Item.Count = expense - income;
        else 								Item.Count = 0;
        
        Item.Color = Color.rgb(255, 96, 0);
        PieData.add(Item);  

        return PieData;
 	}
 	//เอาข้อมูลจาก database ที่ยัดลงใน List มาวาดรูปกราฟ
 	public Bitmap getCirclePie(List<PieDetailsItem> item)
 	{
        //ขนาดของ กราฟวงกลม
        int Size = 105;
        //สีของ background ของรูป ในที่นี้ใช้สีดำ
        int BgColor = 0xff000000;  
        //ส่วนที่เก็บข้อมูลของกราฟมาวาดรูป
        Bitmap mBackgroundImage = Bitmap.createBitmap(Size, Size, Bitmap.Config.RGB_565);
        // เรียกการทำงานของวาดรูปกราฟมาแสดง
        View_PieChart PieChartView = new View_PieChart( this );
        PieChartView.setLayoutParams(new LayoutParams(Size, Size));
        PieChartView.setGeometry(Size, Size, 2, 2, 2, 2);
        PieChartView.setSkinParams(BgColor);
        PieChartView.setData(item, income);
        PieChartView.invalidate();
        //วาดรูปแล้ว return รูปภาพที่เป็น bitmap ออกไป
        PieChartView.draw(new Canvas(mBackgroundImage));
        PieChartView = null;
        
        if(item.get(0).Count > 0)
        	calculatePercent(item);
        else
        	setTextWithNoData();
        
        return mBackgroundImage;
 	}
 	//ใว้คำนวน percent สำหรับแสดงใน
 	public void calculatePercent(List<PieDetailsItem> item)
 	{
    	String LblPercent;
    	float Percent;

    	DecimalFormat FloatFormatter = new DecimalFormat("0.## %");
    	for (int i = 0; i < item.size(); i++) {
    	    Item = (PieDetailsItem) item.get(i);
    	    Percent = (float)Item.Count / (float)income;
    	    LblPercent = FloatFormatter.format(Percent);
    	    if(i  ==0)
    	    {
    	    	txtIncome.setText("รายรับทั้งหมด : "+ Double.toString(Item.Count)+" บาท");
    	    }
    	    else if(i ==1)
    	    {
    	    	txtExpense.setText("รายจ่ายทั้งหมด : "+ Double.toString(Item.Count) +" บาท ("+LblPercent+")");
    	    }
    	    else
    	    {
    	    	txtOverPaid.setText("ยอดเกิน : "+ Double.toString(Item.Count) +" บาท ("+LblPercent+")");
    	    }
    	}
 	}
 	//ตั้งค่าเมื่อไม่มีข้อมูล
 	public void setTextWithNoData()
 	{
 		txtIncome.setText("ไม่มีรายรับในช่วงเวลาที่ระบุ");
 		txtExpense.setText("ไม่มีรายจ่ายในช่วงเวลาที่ระบุ");
 		txtOverPaid.setText("ไม่มียอดเกินในช่วงเวลาที่ระบุ");
 	}
}
