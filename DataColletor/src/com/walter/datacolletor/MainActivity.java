package com.walter.datacolletor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText edt_names, edt_email, edt_phone, edt_address;
    DBController dbcontroller=new DBController(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Enter Details");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9933cc")));
		edt_names=(EditText) findViewById(R.id.edit_names);
		edt_email=(EditText) findViewById(R.id.edit_email);
		edt_phone=(EditText) findViewById(R.id.edit_phone);
		edt_address=(EditText) findViewById(R.id.edit_address);				
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void submit_data(View v)
	{
	  String names=edt_names.getText().toString().trim();	
	  String email=edt_email.getText().toString().trim();	
	  String phone=edt_phone.getText().toString().trim();
	  String location=edt_address.getText().toString().trim();	
	  if(names!="" && email!="" && phone!="" && location!="")
	  {
		  save_in_sqlite (names,email,phone,location);
		  Toast.makeText(getApplicationContext(), "Saved Succesfully in SQLite", Toast.LENGTH_SHORT).show();
	  }else
	  {
		 Toast.makeText(getApplicationContext(), "Empty Fields. Fill in all values", Toast.LENGTH_SHORT).show();  
	  }
		
	}
	private void save_in_sqlite (String names,String email,String phone,String location)
	{
	   dbcontroller.insertUser(names, email, phone, location); 		
	}
	public void check_users(View v)
	{
	  int count=dbcontroller.dbSyncCount();	
	  Toast.makeText(getApplicationContext(), "Number of users is "+count, Toast.LENGTH_SHORT).show(); 
	  Log.d("Json Data", dbcontroller.composeJSONfromSQLite());
	  Intent i=new Intent(this,UsersData.class);
	  startActivity(i);
	}
    public void syn_btn_clicked(View v)
    {
    	
    	AsyncHttpClient client=new AsyncHttpClient();
    	RequestParams params=new RequestParams();
    	if(dbcontroller.dbSyncCount()>0)
    	{
    		String payload=dbcontroller.composeJSONfromSQLite();
    		params.put("payload", payload);
    		client.post("http://10.0.3.2/datasync/details.php", params, new AsyncHttpResponseHandler()
    		{
    			public void onSuccess(int statusCode, String json_string) 
    			{
    				Log.d("Response Success", json_string);
    				update_local_db(json_string);    				
    			};    			
    			public void onFailure(Throwable error, String content)
    			{
    				Log.d("Response Error", content);   				
    			};
    		}
    		);
    		    		
    	}else
    	{
    		Toast.makeText(getApplicationContext(), "All records are in sync! ", Toast.LENGTH_SHORT).show();	
    	}
    	
    }
    private void update_local_db(String json_string)
    {
        try {
			     JSONArray array=new JSONArray(json_string);
			     int size=array.length();
			     for (int i = 0; i < size; i++)
			     {
				   JSONObject jo=(JSONObject) array.get(i);
				   String id=jo.get("id").toString();
				   String status=jo.get("status").toString();
				   dbcontroller.updateSyncStatus(id, status);
				 }		
			} catch (JSONException e) 
			{
              Toast.makeText(getApplicationContext(), "Error Happenned Trying to Process The Response", Toast.LENGTH_SHORT).show();  
			}    	
    }


}
