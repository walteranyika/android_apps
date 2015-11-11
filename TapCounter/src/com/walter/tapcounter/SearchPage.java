package com.walter.tapcounter;


import java.io.File;
import java.util.ArrayList;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class SearchPage extends Activity {
	ListView msgList;
    ArrayList<MessageDetails> details;
    CustomAdapter adapter;
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
//		Bundle extras=getIntent().getExtras();
//		String search_term=extras.getString("data");
		msgList=(ListView) findViewById(R.id.MessageList);
		if(checdbExists())
		{
            new FetchData().execute();
		}else
		{
			Toast.makeText(getApplicationContext(), "No items saved", Toast.LENGTH_LONG).show();
		}
		registerForContextMenu(msgList);
        ActionBar actionBar = getActionBar();
		actionBar.setTitle("Saved Items");
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#31b573")));

	}
  private class FetchData extends AsyncTask<Void, Void, ArrayList<MessageDetails>>
	{

		@Override
		protected ArrayList<MessageDetails> doInBackground(Void... arg0) {
         // MessageDetails Detail;
          ArrayList<MyQuotes> s=new ArrayList<MyQuotes>();
          details = new ArrayList<MessageDetails>();
         // ArrayList<String> f=new ArrayList<String>();
          SQLiteHandler h=new SQLiteHandler(getApplicationContext());
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
			adapter=new CustomAdapter(result ,SearchPage.this);
			msgList.setAdapter(adapter);
		}
		
	}
  public boolean checdbExists()
	   {
			boolean its=false;   
			File database=getApplicationContext().getDatabasePath("tapcount.db");
		    Log.d("checking","Checking in the DB");
			if (!database.exists()) 
			{	  
			   its=false; 
			   Log.d("checking","No DB");
			} else
			{
			   its=true; 
			   Log.d("checking","Yes DB");
			}
			return its;   
	   }
	@Override
  public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
      menu.setHeaderTitle("Saved Item");
	  menu.add(0,v.getId(),0,"Share");
	  menu.add(0,v.getId(),0,"Delete");
	}
	@Override
  public boolean onContextItemSelected(MenuItem item)
	{
		AdapterView.AdapterContextMenuInfo info=(AdapterContextMenuInfo) item.getMenuInfo();
      //Toast.makeText(getApplicationContext(), String.valueOf(info.position), Toast.LENGTH_SHORT).show();
		int i=info.position;
		if(item.getTitle().equals("Share"))
		{
			Intent ii=new Intent();
			ii.setAction(Intent.ACTION_SEND);
			ii.setType("text/plain");
			String data_to_share=details.get(i).getDesc()+"\nCount:"+details.get(i).getSub();
			//ii.putExtra(Intent.EXTRA_SUBJECT,details.get(i).getSub());
			ii.putExtra(Intent.EXTRA_TEXT,  data_to_share);
			startActivity(Intent.createChooser(ii,"Share Via"));
		}else if(item.getTitle().equals("Delete"))
		{
		  SQLiteHandler handler=new SQLiteHandler(getApplicationContext());
		  handler.delete(details.get(i).getSub(), details.get(i).getDesc());
		  details.remove(i);
		  adapter.notifyDataSetChanged();		  
		  handler.close();
			
		}
		return true;
	}
}
