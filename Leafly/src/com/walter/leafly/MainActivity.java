package com.walter.leafly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.ActionBar;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	MediaPlayer m;
	  //ArrayList thats going to hold the search results
	  ArrayList<HashMap<String, Object>> searchResults;
	 
	  //ArrayList that will hold the original Data
	  ArrayList<HashMap<String, Object>> originalValues;
	  LayoutInflater inflater;
	 
	 
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	  
	 setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Leaf Strains");
	    // Enabling Up / Back navigation
	    actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF1919")));
	// final EditText searchBox=(EditText) findViewById(R.id.searchBox);
	 ListView playerListView=(ListView) findViewById(android.R.id.list);
	
	 //get the LayoutInflater for inflating the customomView
	 //this will be used in the custom adapter
	 inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
	 //these arrays are just the data that
	 //I'll be using to populate the ArrayList
	 //You can use our own methods to get the data
	 String names[]={"Phantom Cookies","Presidential OG","Mango Haze","Chiesel",
			 "Elephant Stomper","Emerald Jack"," Shiva Skunk","Afghan Haze",
             "Fishman Kush",
             "Bob Marley",
             " Red Stem Afghani",
             " Albert Walker",
             "Girl Scout Cookies",
             "Kosher Kush",
             "Private Reserve",
             "Ewok",
             "Urban Legend",
             "Afghan Skunk",
             "Willy’s Wonder","Tahoe OG Kush","Space Bomb","Black Jack","Chernobyl",
              "Shiva","Tangerine Dream","White Lightning","Silver Sufer","Sweet Berry",
              "Sour Mango","Skunkberry","Strawberry Ice","Master Kush","Nebula","Northern Lights",
               "Pot of Gold","Romulan","Silver Haze","The Purps",
               "Sugar Kush","THC Bomb","Bubba OG","Apollo 13","Medicine Man","Mango",
               "Lavender","Kali Mist","Ice","Hash Plant","Grape Kush","Flo"};	 
	 String teams[]={"Hybrid",  "Hybrid" ,  "Hybrid","Sativa","Hybrid",  "Hybrid",     
		     "Hybrid","Hybrid",
             "Hybrid",
             "Sativa",
             "Indica",
             "Hybrid",
             "Hybrid",
             "Hybrid",
             "indica",
             "Hybrid",
             "Indica",
             "","","","","","","","","","","",
             "","","","","","","","","","",
             "","","","","","","","","","","",""};	// Integer[] photos={R.drawable.ic_launcher,R.drawable.ic_launcher,
		//	 R.drawable.ic_launcher,R.drawable.ic_launcher,
		//	 R.drawable.ic_launcher,R.drawable.ic_launcher,
		//	 R.drawable.ic_launcher,R.drawable.ic_launcher};
	 
	 originalValues=new ArrayList<HashMap<String,Object>>();
	 
	 //temporary HashMap for populating the
	 //Items in the ListView
	 HashMap<String , Object> temp;
	 
	 //total number of rows in the ListView
	 int noOfPlayers=names.length;
	 
	 //now populate the ArrayList players
	 for(int i=0;i<noOfPlayers;i++)
	 {
	  temp=new HashMap<String, Object>();
	 
	  temp.put("name", names[i]);
	     if(teams[i].isEmpty())
	     {
	    	 temp.put("team", "Hybrid"); 
	     }else
	     {
	    	 temp.put("team", teams[i]);	    	 
	     }
	     
	  originalValues.add(temp);       
	 }
	 //searchResults=OriginalValues initially
	 searchResults=new ArrayList<HashMap<String,Object>>(originalValues);
	 
	 //create the adapter
	 //first param-the context
	 //second param-the id of the layout file
	 //you will be using to fill a row
	 //third param-the set of values that
	 //will populate the ListView
	 final CustomAdapter adapter=new CustomAdapter(this, R.layout.layout_row,searchResults);
	 
	 //finally,set the adapter to the default ListView
	 playerListView.setAdapter(adapter);
	// searchBox.addTextChangedListener(new TextWatcher() {
	 

	 }
	 
	 
	 //define your custom adapter
	private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>>
	 {
	 
	  public CustomAdapter(Context context, int textViewResourceId,
	    ArrayList<HashMap<String, Object>> Strings) {
	 
	   //let android do the initializing :)
	   super(context, textViewResourceId, Strings);
	  }
	 
	 
	  //class for caching the views in a row 
	  private class ViewHolder
	  {
	  // ImageView photo;
	   TextView name,team;
	 
	  }
	 
	  ViewHolder viewHolder;
	 
	  public View getView(int position, View convertView, ViewGroup parent) {
	 
	   if(convertView==null)
	   {
	    convertView=inflater.inflate(R.layout.layout_row, null);
	    viewHolder=new ViewHolder();
	 
	     //cache the views
	    // viewHolder.photo=(ImageView) convertView.findViewById(R.id.photo);
	     viewHolder.name=(TextView) convertView.findViewById(R.id.name);
	     viewHolder.team=(TextView) convertView.findViewById(R.id.team);
	 
	      //link the cached views to the convertview
	      convertView.setTag(viewHolder);
	 
	   }
	   else
	    viewHolder=(ViewHolder) convertView.getTag();	 
	  // int photoId=(Integer) searchResults.get(position).get("photo");	 
	   //set the data to be displayed
	 //  viewHolder.photo.setImageDrawable(getResources().getDrawable(photoId));
	   viewHolder.name.setText(searchResults.get(position).get("name").toString());
	   viewHolder.team.setText(searchResults.get(position).get("team").toString());
	 
	   //return the view to be displayed
	   return convertView;
	  }
	 
	 }
	

		@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
		{
		      super.onListItemClick(l, v, position, id);
             // Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
     	     PlayMedia(position);
           
		}
	public void PlayMedia(int path)
	{
       Intent i=new Intent(getApplicationContext(),LoadLeafs.class);
       i.putExtra("data", path);
       startActivity(i);
       
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	
	}




