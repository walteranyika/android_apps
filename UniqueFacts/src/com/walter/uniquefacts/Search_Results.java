package com.walter.uniquefacts;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.ListActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class Search_Results extends ListActivity implements OnQueryTextListener {
	ArrayList<String> t;
	CustomAdapter adapter;
 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle extras=getIntent().getExtras();
		String search_term=extras.getString("data");
		setContentView(R.layout.search_results);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Results");
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e8641b")));
		t=new ArrayList<String>();
		adapter=new CustomAdapter(this,t);
		if(!search_term.equals(""))
		{
		  SQLiteHandler handle=new SQLiteHandler(getApplicationContext());
		  t=handle.search(search_term);
		  adapter.clear();
		  adapter.addAll(t);
		  
		}
		setListAdapter(adapter);
		 //Get The Action Bar

	}
  @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menuu, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search_second).getActionView();
        searchView.setOnQueryTextListener(this);
		return true;
	
	}
	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onQueryTextSubmit(String search_term) {
		if(!search_term.equals(""))
		{
		  SQLiteHandler handle=new SQLiteHandler(getApplicationContext());
		  t.clear();
		  t=handle.search(search_term);
		  adapter.clear();
		  adapter.addAll(t);
		  adapter.notifyDataSetChanged();
		}else{
			
	      Toast.makeText(getApplicationContext(), "Empty Search Query", Toast.LENGTH_SHORT).show();
		}
		return true;
	}
}
