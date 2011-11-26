package edusoft.android.main;

import edusoft.android.*;
import edusoft.android.main.R;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import edusoft.android.account.AccountActivity;
import edusoft.android.balance.*;
import edusoft.android.report.*;

public class MoneySaverMain extends TabActivity {
	/** Called when the activity is first created. */
	TabHost tabHost;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			Resources res = getResources(); // Resource object to get Drawables
			tabHost = getTabHost(); // The activity TabHost
			TabHost.TabSpec spec; // Resusable TabSpec for each tab
			Intent intent; // Reusable Intent for each tab

			// Create an Intent to launch an Activity for the tab (to be reused)
			intent = new Intent().setClass(this, AccountActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			// Initialize a TabSpec for each tab and add it to the TabHost
			spec = tabHost.newTabSpec("สมุดบัญชี").setIndicator("สมุดบัญชี",
					res.getDrawable(R.layout.ic_tab_account))
					.setContent(intent);
			tabHost.addTab(spec);

			// Do the same for the other tabs
			
			intent = new Intent().setClass(this, BalanceActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			spec = tabHost.newTabSpec("รายรับ-รายจ่าย").setIndicator("รายรับ-รายจ่าย",
					res.getDrawable(R.layout.ic_tab_balance))
					.setContent(intent);
			tabHost.addTab(spec);

			intent = new Intent().setClass(this, ReportActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			spec = tabHost.newTabSpec("รายงาน").setIndicator("รายงาน",
					res.getDrawable(R.layout.ic_tab_report)).setContent(intent);
			tabHost.addTab(spec);

			tabHost.setCurrentTab(0);
		} catch (Exception e) {
			AlertDialog.Builder b = new AlertDialog.Builder(this);
			b.setMessage(e.toString());
			b.show();
		}

	}


	/*public void updateDataChange(int tab) {
		tabHost.
		tabHost.setCurrentTab(tab);
	}*/

}