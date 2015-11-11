package com.walter.italianfortravellers;





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
	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8B8989")));

	TextView tv=(TextView) findViewById(R.id.tv);
	tv.setText("Help\n" +
			  "To use this application, select a category of your choice from the main menu then from the resulting list, tap on any item for audio to play."
			      +
			  "\n\nAbout\n" +
			  "Application Name:Italian For Travellers\n" +
			  "Version: 1.0\n" +
			  "Developer: Waltz Inc");
	
}
}
