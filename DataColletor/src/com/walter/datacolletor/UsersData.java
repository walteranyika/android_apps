package com.walter.datacolletor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class UsersData extends Activity {
	MyAdapter adapter;
	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.users_data);
		list=(ListView) findViewById(R.id.mylist);
		DBController controller=new DBController(getApplicationContext());
		adapter =new MyAdapter(controller.getAllUsers(), getApplicationContext());
		list.setAdapter(adapter);
	}

}
