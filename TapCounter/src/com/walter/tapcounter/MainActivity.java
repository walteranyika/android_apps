package com.walter.tapcounter;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int CUSTOM_DIALOG_ID = 0;
	int count=0;
	TextView tvCount;
 @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	tvCount=(TextView) findViewById(R.id.tvCount);
	SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
    tvCount.setText(sharedprefs.getString("count", "0"));
    
	    ActionBar actionBar = getActionBar();
		actionBar.setTitle("Tap Counter");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#31b573")));
   }
   public void btn_add(View v)
     {
		count++;
		tvCount.setText(String.valueOf(count));
	 }
   public void btn_subtract(View v) {
		// TODO Auto-generated method stub
			count--;
			tvCount.setText(String.valueOf(count));
	 }
   public void btn_reset(View v) 
	 {
			count=0;
			tvCount.setText(String.valueOf(count));
	 }
   @SuppressWarnings("deprecation")
  @Override
   public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	   if(item.getTitle().equals("Help"))
	   {
		   
		   
	   }else if(item.getTitle().equals("Saved Items"))
	   {
		  Intent i=new Intent(MainActivity.this,SearchPage.class); 
		  startActivity(i);
		   
	   }else if(item.getTitle().equals("Save"))
	   {
		
		 showDialog(CUSTOM_DIALOG_ID);
		   
	   }else if(item.getTitle().equals("Help and About"))
		{
           Intent i=new Intent(this,Help_About.class);
           startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
   protected android.app.Dialog onCreateDialog(int id)
   {
   	
   	switch(id)
   	{
   	case CUSTOM_DIALOG_ID:
   		return createExampleDialog();    		
   	default:
   	    return null;
   	}
		
	};
   private Dialog createExampleDialog() {
		 
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Enter Item Name");
       
        // Use an EditText view to get user input.
        final EditText input = new EditText(this);
        input.setId(123);
        builder.setView(input);

       builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {

           @Override
           public void onClick(DialogInterface dialog, int whichButton) {
               String value = input.getText().toString();
               String county=tvCount.getText().toString();
               SQLiteHandler handle=new SQLiteHandler(getApplicationContext());
               handle.Save(value, county);
    		   Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

               return;
           }
       });

       builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
           public void onClick(DialogInterface dialog, int which) {
               return;
           }
       });

       return builder.create();
   }
   @Override
   protected void onPrepareDialog(int id, Dialog dialog) {

       switch (id) {
           case CUSTOM_DIALOG_ID:
               // Clear the input box.
               EditText text = (EditText)dialog.findViewById(123);
               text.setText("");
               break;
       }
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
	    getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
   @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	   SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
	   Editor editor=sharedprefs.edit();
	   editor.putString("count", tvCount.getText().toString());
	   editor.commit();
	   //Toast.makeText(getApplicationContext(), "Activity Destroyed", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
	   @Override
	protected void onPause() {
		  // save the data here
		// TODO Auto-generated method stub
		   SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
		   Editor editor=sharedprefs.edit();
		   editor.putString("count", tvCount.getText().toString());		   
		   editor.commit();
		  // Toast.makeText(getApplicationContext(), "Activity Paused", Toast.LENGTH_SHORT).show();

		super.onPause();
	}
	   @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		   SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
           tvCount.setText(sharedprefs.getString("count", "0"));
           count=Integer.valueOf(sharedprefs.getString("count", "0"));
		   //Toast.makeText(getApplicationContext(), "Activity Resumed", Toast.LENGTH_SHORT).show();

		super.onResume();
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		   SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
           tvCount.setText(sharedprefs.getString("count", "0"));
           count=Integer.valueOf(sharedprefs.getString("count", "0"));
		   //Toast.makeText(getApplicationContext(), "Activity Restarted", Toast.LENGTH_SHORT).show();

		super.onRestart();
	}

}
