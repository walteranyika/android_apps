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

public class Dining_Out extends ListActivity {
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
		actionBar.setTitle("Dining Out");
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
	 String names[]={ "A table for one/two/four, please. ","May I see the menu? ","Is there a children's menu? ","A liter of house red, please. ","What would you recommend? ","I'm vegetarian. ","What's the name of this dish? ","Can I have another knife/spoon? ","Waiter/waitress! ","May we have the check please?"};
	 
	 String teams[]={" Un tavolo per una persona/per due/per quattro, per favore. ","Potrei vedere il menù? ","C'è un menù per bambini? ","Un litro di vino della casa, per favore. ","Cosa ci consiglia? ","Sono vegetariana. ","Come si chiama questo piatto? ","Potrei avere un altro coltello/cucchiaio? ","Cameriere/cameriera! ","Può portarci il conto, per favore? "};

	 
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
                     playMedia("dining/survival007a.wav");
                     //poppDialog(data[0]);
                     break;
                 case 1:
                     //play
                     playMedia("dining/survival007b.wav");
                      //poppDialog("Buta");
                     break;
                 case 2:
                     //play
                     playMedia("dining/survival007c.wav");
                      //poppDialog("Hitsuji");
                     break;
                 case 3:
                     playMedia("dining/survival007d.wav");
                      //poppDialog("Inu");
                     //play
                     break;
                 case 4:
                     //play
                     playMedia("dining/survival007e.wav");
                      //poppDialog("Kaba");
                     break;
                 case 5:

                     playMedia("dining/survival007f.wav");
                      //poppDialog("Kitsune");
                     break;
                 case 6:
                     //play
                     playMedia("dining/survival007g.wav");
                      //poppDialog("Kirin");
                     break;
                 case 7:
                     //play
                     playMedia("dining/survival007h.wav");
                      //poppDialog("Kuma");
                     break;
                 case 8:
                     //play
                     playMedia("dining/survival007i.wav");
                      //poppDialog("Neko");
                     break;
                 case 9:
                     //play
                     playMedia("dining/survival007j.wav");
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

