package com.walter.currencyconveter;


import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class Help_About extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.help_about);
	ActionBar actionBar = getActionBar();
	actionBar.setTitle("Help");
    // Enabling Up / Back navigation
    actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9933cc")));

	TextView tv=(TextView) findViewById(R.id.tv);
	tv.setText("Help\n" +
			  "To use this application,Simply selecctt the currency you want to change from and the currency you want to change to then refresh.\n"+
			  "\n" +
			  "Exchange rates are updated hourly\n\n"+
			  "About\n" +
			  "Application Name:Currency Converter\n" +
			  "Version: 1.0\n" +
			  "Developer: Waltz Inc");
	
}
}
