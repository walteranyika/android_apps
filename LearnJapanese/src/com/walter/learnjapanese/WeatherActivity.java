package com.walter.learnjapanese;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherActivity extends ListActivity {
	//MediaPlayer m;
	  //ArrayList thats going to hold the search results
	  ArrayList<HashMap<String, Object>> searchResults;
	 
	  //ArrayList that will hold the original Data
	  ArrayList<HashMap<String, Object>> originalValues;
	  LayoutInflater inflater;
	 
	 
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	  
	 setContentView(R.layout.exchange_list);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Weather");
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
	 String names[]={"Sunny","Cloudy","Rain","Snow","Thunder","Storm","Fog","Temprature"};
	 
	 String teams[]={"Hare","Kumori","Ame","Yuki","Kaminari","Arashi","Kiri","Ondo"};
	// Integer[] photos={R.drawable.ic_launcher,R.drawable.ic_launcher,
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
	  temp.put("team", teams[i]);   
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
              //Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

		      switch(position)
	             {
		   
	                 case 0:
	                     //play
	                     playMedia("weather/hare.mp3");
	                     //poppDialog(data[0]);
	                     break;
	                 case 1:
	                     //play
	                     playMedia("weather/kumori.mp3");
	                     //poppDialog(data[1]);
	                     break;
	                 case 2:
	                     //play
	                     playMedia("weather/ame.mp3");
	                     //poppDialog(data[2]);
	                     break;
	                 case 3:
	                     playMedia("weather/yuki.mp3");
	                     //poppDialog(data[3]);
	                     //play
	                     break;
	                 case 4:
	                     //play
	                     playMedia("weather/kaminari.mp3");
	                     //poppDialog(data[4]);
	                     break;
	                 case 5:
	                     //play
	                     playMedia("weather/arashi.mp3");
	                     //poppDialog(data[5]);
	                     break;
	                 case 6:
	                     //play
	                     playMedia("weather/kiri.mp3");
	                     //poppDialog(data[6]);
	                     break;
	                 case 7:
	                     //play
	                     playMedia("weather/ondo.mp3");
	                     //poppDialog(data[7]);
	                     break;
	                     
	             
	             }
             
		}
	public void playMedia(String path) {
		
		
		try {
			AssetFileDescriptor desc=getAssets().openFd(path);
			long start=desc.getStartOffset();
			long end=desc.getLength();
			MediaPlayer mm=new MediaPlayer();
			mm.setDataSource(desc.getFileDescriptor(),start,end);
			mm.prepare();
			mm.setVolume(100,100);
		
			mm.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

		}

		}

		
	}




