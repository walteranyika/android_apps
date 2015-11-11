package com.walter.italianfortravellers;




import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Learn Italian");
	    // Enabling Up / Back navigation
	    //actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8B8989")));
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
		
		if(item.getTitle().equals("Help and About"))
		{
			Intent i=new Intent(this,Help_About.class); 
			startActivity(i); 			
		   
		}
		
		return true;
	}
 public void at_the_border(View v)
 {
	Intent i=new Intent(this,At_The_Border.class); 
	startActivity(i);
	 
 }
 public void introductions(View v)
 {
		Intent i=new Intent(this,Introductions.class); 
		startActivity(i); 
 }
 public void farewells(View v)
 {
		Intent i=new Intent(this,Farewells.class); 
		startActivity(i);	 
 }
 public void accommodations(View v)
 {
		Intent i=new Intent(this,Accommodations.class); 
		startActivity(i); 
 }
 public void renting_a_car(View v)
 {
		Intent i=new Intent(this,Renting_A_Car.class); 
		startActivity(i); 
 }
 public void dining_out(View v)
 {
		Intent i=new Intent(this,Dining_Out.class); 
		startActivity(i); 
 }
 public void by_train(View v)
 {
		Intent i=new Intent(this,By_Train.class); 
		startActivity(i); 
 }
 public void by_plane(View v)
 {
		Intent i=new Intent(this,By_Plane.class); 
		startActivity(i); 
 }
 public void health(View v)
 {
		Intent i=new Intent(this,Health.class); 
		startActivity(i); 
 }
 public void greetings(View v)
 {
		Intent i=new Intent(this,Greetings.class); 
		startActivity(i); 
 }
}
