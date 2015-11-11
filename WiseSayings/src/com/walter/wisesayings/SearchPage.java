package com.walter.wisesayings;


import java.util.ArrayList;

import com.example.wisesayings.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class SearchPage extends Activity {
	ListView msgList;
    ArrayList<MessageDetails> details;
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bundle extras=getIntent().getExtras();
		String search_term=extras.getString("data");
		msgList=(ListView) findViewById(R.id.MessageList);
		
        new FetchData().execute(search_term);
        ActionBar actionBar = getActionBar();
		actionBar.setTitle("Results");
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009f3c")));

	}
  private class FetchData extends AsyncTask<String, Void, ArrayList<MessageDetails>>
	{

		@Override
		protected ArrayList<MessageDetails> doInBackground(String... arg0) {
         // MessageDetails Detail;
          ArrayList<MyQuotes> s=new ArrayList<MyQuotes>();
          details = new ArrayList<MessageDetails>();
         // ArrayList<String> f=new ArrayList<String>();
          SQLiteHandler h=new SQLiteHandler(getApplicationContext());
          s=h.search(arg0[0]);
          
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
			msgList.setAdapter(new CustomAdapter(result ,SearchPage.this));
		}
		
	}
}
