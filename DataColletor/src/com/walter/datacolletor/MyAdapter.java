package com.walter.datacolletor;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<Details> _data;
	Context _c;
	public MyAdapter(ArrayList<Details> data,Context c)
	{
		  this._data=data;
		  this._c=c;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View v=convertView;
		if(v==null)
		{
		  LayoutInflater vi=(LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  v=vi.inflate(R.layout.list_item_layout, null);			
		}
		TextView tvNames=(TextView) v.findViewById(R.id.tvNames);
		TextView tvEmail=(TextView) v.findViewById(R.id.tvEmail);
		
		Details dt=_data.get(position);
		tvNames.setText(dt.names);
		tvEmail.setText(dt.email);
		return v;
	}
	
	
	
	
	
	
	
	
	
	
	

}
