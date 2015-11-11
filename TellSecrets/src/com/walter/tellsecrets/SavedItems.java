package com.walter.tellsecrets;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class SavedItems extends ListActivity {
	ArrayList<String> data=null;
	CustomAdapter adapter=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new LoadLocal().execute();
		registerForContextMenu(getListView());
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Secrets");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#669900")));
         // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) 
	{
		// TODO Auto-generated method stub
	      menu.setHeaderTitle("Secrets");
		  menu.add(0,v.getId(),0,"Share");
		  menu.add(0,v.getId(),0,"Delete");
		
	}
	public boolean onContextItemSelected(android.view.MenuItem item)
	{
		AdapterView.AdapterContextMenuInfo info=(AdapterContextMenuInfo) item.getMenuInfo();
		int i=info.position;
		if(item.getTitle().equals("Share"))
		{
			Intent ii=new Intent();
			ii.setAction(Intent.ACTION_SEND);
			ii.setType("text/plain");
			ii.putExtra(Intent.EXTRA_SUBJECT, "Secrets:");
			ii.putExtra(Intent.EXTRA_TEXT, data.get(i));
			startActivity(Intent.createChooser(ii,"Share Via"));
		}else if(item.getTitle().equals("Delete"))
		{
			SQLiteHandler handle=new SQLiteHandler(getApplicationContext());
			handle.delete(data.get(i));
			new LoadLocal().execute();
			adapter.notifyDataSetChanged();
     		Toast.makeText(getApplicationContext(), "Deleted From Favourites", Toast.LENGTH_SHORT).show();
		}
		return false;
	}
	private class LoadLocal extends AsyncTask<Void, Void, ArrayList<String>>{

		@Override
		protected ArrayList<String> doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			data=new ArrayList<String>();
			SQLiteHandler handle=new SQLiteHandler(getApplicationContext());
			data=handle.getData();
			return data;
		}
		@Override
		protected void onPostExecute(ArrayList<String> result) {
			// TODO Auto-generated method stub
			adapter=new CustomAdapter(SavedItems.this, result);
			setListAdapter(adapter);
			//super.onPostExecute(result);
		}
		
	}
}
