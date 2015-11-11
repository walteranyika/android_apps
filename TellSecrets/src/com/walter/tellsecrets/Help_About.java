package com.walter.tellsecrets;

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
    actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#669900")));

	TextView tv=(TextView) findViewById(R.id.tv);
	tv.setText("Help\n" +
			  "To use this application, simply refresh by " +
			  "pressing the refresh icon after the application has loaded.\n" +
			  "You can also save a favourite secret by long pressing it and selecting save"+
			  "\nData chargres might apply."+
			  "\n" +
			  "About\n" +
			  "Application Name: Tell Secrets\n" +
			  "Version: 1.0\n" +
			  "Developer: Waltz Inc");
	
}
}
