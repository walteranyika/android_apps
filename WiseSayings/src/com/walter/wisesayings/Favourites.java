package com.walter.wisesayings;

import java.io.File;
import java.util.ArrayList;

import com.example.wisesayings.R;



import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class Favourites  extends Activity{
	ListView msgList;
    ArrayList<MessageDetails> details;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Favourites");
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009f3c")));
		msgList=(ListView) findViewById(R.id.MessageList);
		if(checdbExists())
		{
			new FetchData().execute();	
		}
		else
		{
			Toast.makeText(getApplicationContext(), "No items added in your favourites yet", Toast.LENGTH_SHORT).show();
		}
		
	}
	private class FetchData extends AsyncTask<Void, Void, ArrayList<MessageDetails>>
	{

		@Override
		protected ArrayList<MessageDetails> doInBackground(Void... arg0) {
         // MessageDetails Detail;
          ArrayList<MyQuotes> s=new ArrayList<MyQuotes>();
          details = new ArrayList<MessageDetails>();
         // ArrayList<String> f=new ArrayList<String>();
          SQLiteHandler2 h=new SQLiteHandler2(getApplicationContext());
          s=h.getDataa();
          
          if(s.size()>0)
          {
              int x=s.size();	
          	for (int i = 0; i < x; i++)
          	{				
               MessageDetails d=new MessageDetails();
          	 d.setSub(s.get(i)._preacher);
               d.setDesc(s.get(i)._quote);
               //Log.d("data", s.get(i)._preacher);
               details.add(d);
			}    	
     
		}
      return details;
  }
		@Override
		protected void onPostExecute(ArrayList<MessageDetails> result)
		{
			if(result.size()>0){
			msgList.setAdapter(new CustomAdapter(result ,Favourites.this));
			}else{
				Toast.makeText(getApplicationContext(), "No favourites Added", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	public boolean checdbExists()
	   {
			boolean its=false;   
			File database=getApplicationContext().getDatabasePath("xtianf.db");
		   // Log.d("checking","Checking in the DB");
			if (!database.exists()) 
			{	  
			   its=false; 
			   //Log.d("checking","No DB");
			} else
			{
			   its=true; 
			  // Log.d("checking","Yes DB");
			}
			return its;   
	   }
}
