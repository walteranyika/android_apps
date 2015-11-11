package com.walter.simtest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
  EditText edt_name;
  EditText edt_location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edt_name=(EditText) findViewById(R.id.editText1);
		edt_location=(EditText) findViewById(R.id.editText2);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
   
	public void save_data(View v)
	{
	   String names=edt_name.getText().toString();
	   String location=edt_location.getText().toString();
	   AsyncHttpClient client=new AsyncHttpClient();
	   RequestParams params=new RequestParams();
	   params.put("names", names);
	   params.put("location", location);
	   client.post("", params, new AsyncHttpResponseHandler()
	   {
		  @Override
		public void onSuccess(String content) {
			// TODO Auto-generated method stub
			super.onSuccess(content);
		}
		  @Override
		public void onFailure(int statusCode, Throwable error, String content) {
			// TODO Auto-generated method stub
			super.onFailure(statusCode, error, content);
		}
	   });
		
	}
}
