package com.walter.pickuplines;

import java.io.File;
import java.util.ArrayList;

import com.example.pickuplines.R;


import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Favour extends ListActivity{
	ArrayList<String> d;
	CustomAdapter adapter;
	@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.favour);
		
			
			if(checdbExists())
			{
				d=new ArrayList<String>();
				SQLiteHandler2 h2=new SQLiteHandler2(getApplicationContext());
				d=h2.getData();
				adapter=new CustomAdapter(this, d);
				setListAdapter(adapter);
			}else
			{
			Toast.makeText(getApplicationContext(), "No Items in Your favourites", Toast.LENGTH_LONG).show();				
			}
			registerForContextMenu(getListView());
			 //Get The Action Bar
			ActionBar actionBar = getActionBar();
			actionBar.setTitle("Favourites");
			actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ea6b48")));
	         // Enabling Up / Back navigation
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        
		}
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			// TODO Auto-generated method stub
		      menu.setHeaderTitle("Favourites");
			  menu.add(0,v.getId(),0,"Share");
			  menu.add(0,v.getId(),0,"Delete");
			
		}
		@Override
		public boolean onContextItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
			AdapterView.AdapterContextMenuInfo info=(AdapterContextMenuInfo) item.getMenuInfo();
			int i=info.position;
			if(item.getTitle().equals("Share"))
			{
				Intent ii=new Intent();
				ii.setAction(Intent.ACTION_SEND);
				ii.setType("text/plain");
				ii.putExtra(Intent.EXTRA_SUBJECT, "Proverb:");
				ii.putExtra(Intent.EXTRA_TEXT, d.get(i));
				startActivity(Intent.createChooser(ii,"Share Via"));
			}else if(item.getTitle().equals("Delete"))
			{
				SQLiteHandler2 handle=new SQLiteHandler2(getApplicationContext());
				handle.delete(d.get(i));
				Toast.makeText(getApplicationContext(), "Item Deleted From Favourites", Toast.LENGTH_SHORT).show();
			    d.clear();			    
			    d=handle.getData();
			    adapter.clear();
			    adapter.addAll(d);
			    adapter.notifyDataSetChanged();
			}
			return true;
		}
	    public boolean checdbExists()
	   {
			boolean its=false;   
			File database=getApplicationContext().getDatabasePath("fav.db");
		
			if (!database.exists()) 
			{	  
			   its=false; 
			} else
			{
			   its=true; 
			}
			return its;   
	   }
}
