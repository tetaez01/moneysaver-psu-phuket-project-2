package edusoft.android.reuse;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import android.app.Activity;
import android.content.res.Resources;
import edusoft.android.main.R;

public class Utility extends Activity{
	boolean isNull = false;
	boolean isNotNull = false;
	//Toast.makeText(getApplicationContext(),dbHelp.getBankAcronymByAccountId(balanceObject.getAccountId()), Toast.LENGTH_LONG).show();
	public boolean isNull(String value) {
		if (value == "" || value == null)
			isNull = true;
		return isNull;
	}

	public boolean isNotNull(String value) {
		if (value != "" || value != null)
			isNotNull = true;
		return isNotNull;
	}
	
	public String getCurrentDate(){
		final Calendar c = Calendar.getInstance();
		return Integer.toString(c.get(Calendar.DATE))+ "/"+ Integer.toString(c.get(Calendar.MONTH)+1)+"/"+ Integer.toString(c.get(Calendar.YEAR));
	}
	
	public String getCurrentDay(){
		final Calendar c = Calendar.getInstance();
		return Integer.toString(c.get(Calendar.DATE));
	}
	
	public String getCurrentMonth(){
		final Calendar c = Calendar.getInstance();
		return Integer.toString(c.get(Calendar.MONTH)+1);
	}
	
	public String getCurrentYear(){
		final Calendar c = Calendar.getInstance();
		return Integer.toString(c.get(Calendar.YEAR));
	}
	
	public String addDecimal(String number){
		if(number.indexOf('.') > 0){
			if(number.length() == (number.indexOf('.')+2))
				number += "0";				
		}else{
			number += ".00";
		}
		return number;
	}
}
