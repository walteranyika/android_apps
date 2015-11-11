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

public class AnimalsActivity extends ListActivity {
	MediaPlayer m;
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
		actionBar.setTitle("Animals");
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
	 String names[]={"Animals",     "Pig",  "Sheep", "Dog", "Hippopotomus" ,"Fox",    "Girraffe",  "Bear",  "Cat",   "Mouse",  "Wolf",   "Lion",  "Camel",  "Squirrel", "Monkey",   "Deer",  "Zebra",     "Tiger",  "Bird",     "Rabbit",  "Cow",   "Horse", "Goat",  "Elephant"};
	 
	 String teams[]={"Doubotsu", "Buta", "Hitsuji","Inu", "Kaba",        "Kitsune", "Kirin",     "kuma",  "neko",  "nezumi", "ookami", "raion", "rakuda", "risu",    "Saru",      "Shika", "Shimauma",  "Tora",   "Tori",     "Usagi",   "Ushi",  "Uma",   "Yagi",  "Zou"};
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
                     playMedia("animals/doubutsu.mp3");
                     //poppDialog(data[0]);
                     break;
                 case 1:
                     //play
                     playMedia("animals/buta.mp3");
                      //poppDialog("Buta");
                     break;
                 case 2:
                     //play
                     playMedia("animals/hitsuji.mp3");
                      //poppDialog("Hitsuji");
                     break;
                 case 3:
                     playMedia("animals/inu.mp3");
                      //poppDialog("Inu");
                     //play
                     break;
                 case 4:
                     //play
                     playMedia("animals/kaba.mp3");
                      //poppDialog("Kaba");
                     break;
                 case 5:

                     playMedia("animals/kitsune.mp3");
                      //poppDialog("Kitsune");
                     break;
                 case 6:
                     //play
                     playMedia("animals/kirin.mp3");
                      //poppDialog("Kirin");
                     break;
                 case 7:
                     //play
                     playMedia("animals/kuma.mp3");
                      //poppDialog("Kuma");
                     break;
                 case 8:
                     //play
                     playMedia("animals/neko.mp3");
                      //poppDialog("Neko");
                     break;
                 case 9:
                     //play
                     playMedia("animals/nezumi.mp3");
                      //poppDialog("Nezumi");
                     break;
                 case 10:
                     //play
                     playMedia("animals/ookami.mp3");
                      //poppDialog("Ookami");
                     break;
                 case 11:
                     //play
                     playMedia("animals/raion.mp3");
                      //poppDialog("Raion");
                     break;
                 case 12:
                     //play
                     playMedia("animals/rakuda.mp3");
                      //poppDialog("Rakuda");
                     break;
                 case 13:
                     //play
                     playMedia("animals/risu.mp3");
                      //poppDialog("Risu");
                     break;
                 case 14:
                     //play
                    // "Deer","Zebra","Tiger","Bird","Rabbit","Cow","Horse","Goat","Elephant"};
                     //"Shika","Shimauma","Tora","Tori","Usagi","Ushi","Uma","Yagi","Zou"}
                     playMedia("animals/saru.mp3");
                      //poppDialog("Saru");
                     break;
                 case 15:
                     //play
                     playMedia("animals/shika.mp3");
                      //poppDialog("Shika");
                     break;                   
                 case 16:
                     //play
                     playMedia("animals/shimauma.mp3");
                      //poppDialog("Shimauma");
                     break;
                 case 17:
                     //play
                     playMedia("animals/tora.mp3");
                      //poppDialog("Tora");
                     break;
                 case 18:
                     //play
                     playMedia("animals/tori.mp3");
                      //poppDialog("Tori");
                     break;
                 case 19:
                     //play
                     playMedia("animals/usagi.mp3");
                      //poppDialog("Usagi");
                     break;
                 case 20:
                     //play
                     playMedia("animals/ushi.mp3");//ok
                      //poppDialog("Ushi");
                     break;
                 case 21:
                     //play
                     playMedia("animals/uma.mp3");//ok
                      //poppDialog("Uma");
                     break;
                 case 22:
                     //play
                     playMedia("animals/yagi.mp3");//ok
                      //poppDialog("Yagi");
                     break;
                 case 23:
                     //play
                     playMedia("animals/zou.mp3");//ok
                      //poppDialog("Zou");
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




