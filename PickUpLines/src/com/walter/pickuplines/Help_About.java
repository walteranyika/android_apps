package com.walter.pickuplines;

import com.example.pickuplines.R;

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
	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ea6b48")));

	TextView tv=(TextView) findViewById(R.id.tv);
	tv.setText("Help\n" +
			  "To use this application, simply refresh by " +
			  "pressing the refresh icon after the application has loaded.\n" +
			  "The application might delay loading the items the first time it is installed. Please give it a few seconds to initialize then refresh.\n" +
			  "\n" +
			  "About\n" +
			  "Application Name: PickUp Lines\n" +
			  "Version: 1.0\n" +
			  "Developer: Waltz Inc");
	
}
}
