package com.walter.italianfortravellers;

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

public class Renting_A_Car extends ListActivity {
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
		actionBar.setTitle("Renting A Car");
	    // Enabling Up / Back navigation
	    actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8B8989")));
	// final EditText searchBox=(EditText) findViewById(R.id.searchBox);
	 ListView playerListView=(ListView) findViewById(android.R.id.list);
	 
	 //get the LayoutInflater for inflating the customomView
	 //this will be used in the custom adapter
	 inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
	 //these arrays are just the data that
	 //I'll be using to populate the ArrayList
	 //You can use our own methods to get the data
	 String names[]={"Where can I rent a car? ","I'd like to rent a car. ","How much will it cost? ","Is there a mileage charge? ","How much will I have to deposit? ","Will I have to pay for the gasoline myself? ","I'd like an automatic/manual. ","When/Where can I pick up the car? ","When do I have to return it? ","Will somebody be there when I bring the car back?"};
	 
	 String teams[]={"Dove posso noleggiare una macchina? ","Vorrei noleggiare una macchina. ","Quanto costa? ","C'è un supplemento per il chilometraggio? ","Quanto devo dare come cauzione? ","Devo pagare io la benzina? ","Vorrei una macchina con il cambio automatico/manuale. ","Quando/Dove posso ritirare la macchina? ","Quando devo riportarla? ","Ci sarà qualcuno quando riporto la macchina? "};

	 
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
	 
	 searchResults=new ArrayList<HashMap<String,Object>>(originalValues);
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
                     playMedia("renting/survival006a.wav");
                     //poppDialog(data[0]);
                     break;
                 case 1:
                     //play
                     playMedia("renting/survival006b.wav");
                      //poppDialog("Buta");
                     break;
                 case 2:
                     //play
                     playMedia("renting/survival006c.wav");
                      //poppDialog("Hitsuji");
                     break;
                 case 3:
                     playMedia("renting/survival006d.wav");
                      //poppDialog("Inu");
                     //play
                     break;
                 case 4:
                     //play
                     playMedia("renting/survival006e.wav");
                      //poppDialog("Kaba");
                     break;
                 case 5:

                     playMedia("renting/survival006f.wav");
                      //poppDialog("Kitsune");
                     break;
                 case 6:
                     //play
                     playMedia("renting/survival006g.wav");
                      //poppDialog("Kirin");
                     break;
                 case 7:
                     //play
                     playMedia("renting/survival006h.wav");
                      //poppDialog("Kuma");
                     break;
                 case 8:
                     //play
                     playMedia("renting/survival006i.wav");
                      //poppDialog("Neko");
                     break;
                 case 9:
                     //play
                     playMedia("renting/survival006j.wav");
                      //poppDialog("Nezumi");
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

