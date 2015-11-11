package com.walter.wisesayings;

import com.example.wisesayings.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Help_About extends Activity {
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.help_about);
	TextView tv=(TextView) findViewById(R.id.tv);
	tv.setText("Help\n" +
			  "To use this application, simply refresh by " +
			  "pressing the refresh icon after the application has loaded.\n" +
			  "The application might delay loading the items the first time it is installed. Please give it a few seconds to initialize then refresh.\n" +
			  "\n " +
			  "About\n" +
			  "Application Name: Wise Sayings\n" +
			  "Version: 1.0\n" +
			  "Developer:Waltz Inc");
}
}
