package com.walter.leafly;





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
	actionBar.setTitle("Help and About");
    // Enabling Up / Back navigation
    actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF1919")));

	TextView tv=(TextView) findViewById(R.id.tv);
	tv.setText("Help\n" +
			  "To use this application, select a leaf strain to view its details.(Picture). You can also swipe the screen to right or left from the details page."
			      +
			  "\n\nAbout\n" +
			  "Application Name:Leafly\n" +
			  "Version: 1.0\n" +
			  "Developer: Waltz Inc");
	
}
}
