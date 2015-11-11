package com.example.parsingdata;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	/*	    
	 * FE74VA752 Confirmed. You have received Ksh1,030.00 from CHARLES NYAKERUMA 254720014637 on 10/6/14 at 5:48 PM New M-PESA balance is Ksh1,062.00.PIN YAKO SIRI YAKO
*/
		String sms="FE74VA752 Confirmed. You have received Ksh1,030.00 from CHARLES NYAKERUMA 254720014637 on 10/6/14 at 5:48 PM New M-PESA balance is Ksh1,062.00.PIN YAKO SIRI YAKO";
		sms=sms.toLowerCase();
		if(sms.contains("new m-pesa balance") && sms.contains("confirmed"))
    	{
    	  //recieved monies
    	 
    		
			//received
    		if(sms.contains("received"))
    		{
    		 int x= sms.indexOf("ksh")+1;	
    		 int y=sms.indexOf("from");    			
    		 Toast.makeText(getApplicationContext(), String.valueOf(x)+" : "+String.valueOf(y)+" :"+ sms.substring(x+2, y-1), Toast.LENGTH_SHORT).show();	
    		TextView tv=(TextView) findViewById(R.id.tv);
    		tv.setText(sms.substring(x+2, y-1));
    		}
    	     	 
    	  //withdrawn monies
    	  //pay bill sent to
    	  //repaid a loan
    		
    	}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
