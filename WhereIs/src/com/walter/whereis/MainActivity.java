package com.walter.whereis;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void start(View v)
	{
	 setRecurringAlarm(getApplicationContext());
	 Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_SHORT).show();
	}
	public void stop(View v)
	{
	 cancelAlarm(getApplicationContext());	
	 Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_SHORT).show();
	}
    private void setRecurringAlarm(Context context)
   {
	  Intent i=new Intent(context,LocationReceiver.class);
	  PendingIntent pint=PendingIntent.getBroadcast(context, 0, i,0);
	  AlarmManager alarms=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
	  //after ten min
	  alarms.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60*3, pint);
   }
    private void cancelAlarm(Context context)
   {
	   Intent i=new Intent(context,LocationReceiver.class);
		  PendingIntent pint=PendingIntent.getBroadcast(context, 0, i,0);
		  AlarmManager alarms=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
		  //cancel
		  alarms.cancel(pint); 
	   
   }
}
