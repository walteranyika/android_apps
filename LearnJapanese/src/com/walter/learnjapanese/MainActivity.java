package com.walter.learnjapanese;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
		actionBar.setTitle("Holy Me");
        // Enabling Up / Back navigation
     //   actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF1919")));
	}

	@Override
  public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	   if(item.getItemId()==R.id.action_help)
	   {
		 Intent i=new Intent(getApplicationContext(),Help_About.class);  
		  startActivity(i); 
	   }
		return true;
   }
   public void numbers(View v)
   {
	  Intent i=new Intent(this,NameActivity.class); 
	  startActivity(i);
	   
   }
   public void animals(View v)
   {
	  Intent i=new Intent(this,AnimalsActivity.class); 
	  startActivity(i);
	   
   }
   public void calendars(View v)
   {
	  Intent i=new Intent(this,CalendarActivity.class); 
	  startActivity(i);
	   
   }
   public void week_days(View v)
   {
	  Intent i=new Intent(this,DaysActivity.class); 
	  startActivity(i);
	   
   }
   public void greetings(View v)
   {
	  Intent i=new Intent(this,GreetingActivity.class); 
	  startActivity(i);
	   
   }
   public void time(View v)
   {
	  Intent i=new Intent(this,TimeActivity.class); 
	  startActivity(i);
	   
   }
   public void weathers(View v)
   {
	  Intent i=new Intent(this,WeatherActivity.class); 
	  startActivity(i);
	   
   }
   public void colors(View v)
   {
	  Intent i=new Intent(this,ColorsActivity.class); 
	  startActivity(i);
	   
   }
}
