package com.walter.tapcounter;

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
			  "pressing the large green area to start counting.\n" +
			  "You can also save counted items for future refrence"+			  
			  "\n" +
			  "About\n" +
			  "Application Name: Tap Counter\n" +
			  "Version: 1.0\n" +
			  "Developer: Waltz Inc");
	
}
}
