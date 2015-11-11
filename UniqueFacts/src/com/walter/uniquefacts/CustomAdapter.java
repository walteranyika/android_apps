package com.walter.uniquefacts;

import java.util.ArrayList;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final ArrayList<String> presidents;
	//private final Integer[] imagesId;
  
	public CustomAdapter(Activity context, ArrayList<String> presidents) 
	{	
		super(context,R.layout.layoutrow,presidents);
		this.context=context;
		this.presidents=presidents;
		//this.imagesId=imagesId;
		
    }
	static class ViewContainer
	{
		//public ImageView imageview;
		public TextView title;
		//public TextView description;
		
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewContainer viewContainer;
		View rowView=view;
		//---if the row is displayed for the first time---
		if (rowView == null) {
		Log.d("CustomArrayAdapter", "New");
		LayoutInflater inflater = context.getLayoutInflater();
		rowView = inflater.inflate(R.layout.layoutrow, null, true);
		//---create a view container object---
		viewContainer = new ViewContainer();
		//---get the references to all the views in the row---
		viewContainer.title = (TextView)rowView.findViewById(R.id.txtPresidentName);
		
		
		//TextView myTextView=(TextView)findViewById(R.id.textBox);
		//Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/mytruetypefont.ttf");
		//myTextView.setTypeface(typeFace);
		
		
		
		//viewContainer.description = (TextView)	rowView.findViewById(R.id.txtDescription);
		//viewContainer.imageview = (ImageView)rowView.findViewById(R.id.icon);
		//---assign the view container to the rowView---
		rowView.setTag(viewContainer);
		} 
		else {
		//---view was previously created; can recycle---
		Log.d("CustomArrayAdapter", "Recycling");
		//---retrieve the previously assigned tag to get
		// a reference to all the views; bypass the findViewByID() process,
		// which is computationally expensive---
		viewContainer = (ViewContainer) rowView.getTag();
		}
		//---customize the content of each row based on position---
		viewContainer.title.setText(presidents.get(position));
		//viewContainer.description.setText(presidents.get(position));
		//viewContainer.imageview.setImageResource(R.drawable.arrow);		
		return rowView;

	}
}
