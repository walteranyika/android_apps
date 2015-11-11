package com.walter.currencyconveter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ToActivity extends ListActivity {
	 
	  //ArrayList thats going to hold the search results
	  ArrayList<HashMap<String, Object>> searchResults;
	 
	  //ArrayList that will hold the original Data
	  ArrayList<HashMap<String, Object>> originalValues;
	  LayoutInflater inflater;
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	  
	 setContentView(R.layout.main_active);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Help");
	    // Enabling Up / Back navigation
	    actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9933cc")));
	 final EditText searchBox=(EditText) findViewById(R.id.searchBox);
	 ListView playerListView=(ListView) findViewById(android.R.id.list);
	 
	 //get the LayoutInflater for inflating the customomView
	 //this will be used in the custom adapter
	 inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
	 //these arrays are just the data that
	 //I'll be using to populate the ArrayList
	 //You can use our own methods to get the data
	 String names[]={"United Arab Emirates ","Afghanistan ","Albania ","Armenia ","Netherlands Antilles ","Angola ","Argentina ","Australia ","Aruba ","Azerbaijan ","Bosnia and Herzegovina ","Barbados ","Bangladesh ","Bulgaria ","Bahrain ","Burundi ","Bermuda ","Brunei ","Bolivia ","Bolivia ","Brazil ","Bahamas ","Bhutan ","Botswana ","Belarus ","Belize ","Canada ","Democratic Republic of Congo ","Switzerland ","Switzerland and Liechtenstein ","Switzerland ","Chile ","Chile ","Mainland China ","Colombia ","Colombia ","Costa Rica ","Cuba ","Cape Verde ","Cyprus ","Czech Republic ","Djibouti ","Denmark ","Dominican Republic ","Algeria ","Estonia ","Egypt ","Eritrea ","Ethiopia ","European Union ","Fiji ","Falkland Islands ","United Kingdom ","Georgia ","Ghana ","Gibraltar ","Gambia ","Guinea ","Guatemala ","Guyana ","Hong Kong Special Administrative Region ","Honduras ","Croatia ","Haiti ","Hungary ","Indonesia ","Israel ","Bhutan India ","Iraq ","Iran ","Iceland ","Jamaica ","Jordan ","Japan ","Kenya ","Kyrgyzstan ","Cambodia ","Comoros ","North Korea ","South Korea ","Kuwait ","Cayman Islands ","Kazakhstan ","Laos ","Lebanon ","Sri Lanka ","Liberia ","Lesotho ","Lithuania ","Latvia ","Libya ","Morocco and Western Sahara ","Moldova ","Madagascar ","Yugoslav and Republic of Macedonia","Myanmar ","Mongolia ","Macau Special Administrative Region ","Mauritania ","Malta ","Mauritius ","Maldives ","Malawi ","Mexico ","Mexico ","Malaysia ","Mozambique ","Namibia ","Nigeria ","Nicaragua ","Norway ","Nepal "," New Zealand ","Oman ","Panama ","Peru ","Papua New Guinea ","Philippines ","Pakistan ","Poland ","Paraguay ","Qatar ","Romania ","Serbia ","Russia ","Rwanda ","Saudi Arabia ","Solomon Islands ","Seychelles ","Sudan ","Sweden ","Singapore ","Saint Helena ","Slovakia ","Sierra Leone ","Somalia ","Suriname ","Sao Tome and Principle ","Syria ","Swaziland ","Thailand ","Tajikistan ","Turkmenistan ","Tunisia ","Tonga ","Turkey ","Trinidad and Tobago ","Taiwan ","Tanzania ","Ukraine ","Uganda ","United States ","Uruguay ","Uzbekistan ","Venezuela ","Vietnam ","Vanuatu ","Samoa ","French Polynesia ","Yemen ","South Africa ","Zambia","Zimbabwe ","Senegal"};
	 
	 String teams[]={"AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BOV","BRL","BSD","BTN","BWP","BYR","BZD","CAD","CDF","CHE","CHF","CHW","CLF","CLP","CNY","COP","COU","CRC","CUP","CVE","CYP","CZK","DJF","DKK","DOP","DZD","EEK","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GEL","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","INR","IQD","IRR","ISK","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MTL","MUR","MVR","MWK","MXN","MXV","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SKK","SLL","SOS","SRD","STD","SYP","SZL","THB","TJS","TMM","TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEB","VND","VUV","WST","XPF","YER","ZAR","ZMK","ZWD","XOF"};
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
	 searchBox.addTextChangedListener(new TextWatcher() {
	 
	 public void onTextChanged(CharSequence s, int start, int before, int count) {
	   //get the text in the EditText
	   String searchString=searchBox.getText().toString();
	   int textLength=searchString.length();
	   searchResults.clear();
	 
	   for(int i=0;i<originalValues.size();i++)
	   {
	  String playerName=originalValues.get(i).get("name").toString();
	  if(textLength<=playerName.length()){
	  //compare the String in EditText with Names in the ArrayList
	    if(searchString.equalsIgnoreCase(playerName.substring(0,textLength)))
	    searchResults.add(originalValues.get(i));
	  }
	   }
	 
	   adapter.notifyDataSetChanged();
	 }
	 
	 public void beforeTextChanged(CharSequence s, int start, int count,
	     int after) {
	 
	 
	   }
	 
	   public void afterTextChanged(Editable s) {
	 
	 
	   }
	  });
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		   if(keyCode==KeyEvent.KEYCODE_BACK)
		   {
				finish();
		   }
		return super.onKeyDown(keyCode, event);
	}
		@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		    super.onListItemClick(l, v, position, id);
		      Log.d("Click", String.valueOf(position));
		      String country=searchResults.get(position).get("name").toString();
		      String code=searchResults.get(position).get("team").toString();
		      Log.d("Country", country);
		      Log.d("Country", code);
			   SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
			   Editor editor=sharedprefs.edit();
			   editor.putString("to_country",country);
			   editor.putString("to_code",code);
			   editor.commit();	
			   Intent i=new Intent(this,MainActivity.class);
			   i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);			   
			   startActivity(i); 
			   finish();
		}
	}




