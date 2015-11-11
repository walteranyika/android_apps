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

public class NameActivity extends ListActivity {
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
		actionBar.setTitle("Counting");
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
	 String names[]={"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven", "Twelve","Thirteen","Twenty one","Twenty Two","Thirty","Thirty One","Thirty Two","One Hundred","One Fifty","One Thousand","1500",   "2000",  "10,000"};
	 
	 String teams[]={"Ichi","Ni","San","Shi","Go","Roku","Shichi","Hachi","Kyuu","Juu","Juuichi","Juuni", "Juusan",   "Nijuuichi", "Nijuuni",  "Sanjuu","Sanjuuichi", "Sanjuuni", "Hyaku",      "Hyakugojuu","Sen",        "SengoHyaku","Nisen","Ichiman"};
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
	                     playBeep("numbers/ichi.mp3");
	                     break;
	                 case 1:
	                     //play
	                     playBeep("numbers/ni.mp3");
	                     //poppDialog(data[1]);
	                     break;
	                 case 2:
	                     //play
	                     playBeep("numbers/san.mp3");
	                     //poppDialog(data[2]);
	                     break;
	                 case 3:
	                     playBeep("numbers/shi.mp3");
	                     //poppDialog(data[3]);
	                     //play
	                     break;
	                 case 4:
	                     //play
	                     playBeep("numbers/go.mp3");
	                     //poppDialog(data[4]);
	                     break;
	                 case 5:
	                     //play
	                     playBeep("numbers/roku.mp3");
	                     //poppDialog(data[5]);
	                     break;
	                 case 6:
	                     //play
	                     playBeep("numbers/shichi.mp3");
	                     //poppDialog(data[6]);
	                     break;
	                 case 7:
	                     //play
	                     playBeep("numbers/hachi.mp3");
	                     //poppDialog(data[7]);
	                     break;
	                 case 8:
	                     //play
	                     playBeep("numbers/kyuu.mp3");
	                     //poppDialog(data[8]);
	                     break;
	                 case 9:
	                     //play
	                     playBeep("numbers/juu.mp3");
	                     //poppDialog(data[9]);
	                     break;
	                     //-----------------------------------------
	                 case 10:
	                     //play
	                     playBeep("numbers/juuichi.mp3");//11
	                     //poppDialog("Juuichi");
	                     break;
	                 case 11:
	                     //play
	                     playBeep("numbers/juuni.mp3");//12
	                     //poppDialog("Juuni");
	                     break;
	                 case 12:
	                     //play
	                     playBeep("numbers/juusan.mp3");//13
	                     //poppDialog("Juusan");
	                     break;
	                 case 13:
	                     //play
	                     playBeep("numbers/nijuuichi.mp3");//21
	                     //poppDialog("Nijuuichi");
	                     break;
	                 case 14:
	                     //play
	                     playBeep("numbers/nijuuni.mp3");//22//done
	                     //poppDialog("Nijuuni");
	                     break;
	                 case 15:
	                     //play
	                     playBeep("numbers/sanjuu.mp3");//30
	                     //poppDialog("Sanjuu");
	                     break;
	                 case 16:
	                     //play
	                     playBeep("numbers/sanjuuichi.mp3");//31
	                     //poppDialog("Sanjuuichi");
	                     break;
	                 case 17:
	                     //play
	                     playBeep("numbers/sanjuuni.mp3");
	                     //poppDialog("Sanjuuni");
	                     break;
	                 case 18:
	                     //play
	                     playBeep("numbers/hyaku.mp3");//100
	                     //poppDialog("Hyaku");
	                     break;
	                 case 19:
	                     //play
	                     playBeep("numbers/hyakugojuu.mp3");//150//Done
	                     //poppDialog("Hyakugojuu");
	                     break;
	                 case 20:
	                     //play
	                     playBeep("numbers/sen.mp3");//1000
	                     //poppDialog("Sen");
	                     break;
	                 case 21:
	                     //play
	                     playBeep("numbers/sengohyaku.mp3");//1500
	                     
	                     break;
	                 case 22:
	                     //play
	                     playBeep("numbers/nisen.mp3");
	                     
	                     break;
	                 case 23:
	                     //play
	                     playBeep("numbers/ichiman.mp3");//10000
	                     
	                     break;
	                     
	                     
	                     
	             
	             }
             
		}
	public void playBeep(String path) {
		
		
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




