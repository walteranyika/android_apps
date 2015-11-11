package com.walter.refresher;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
   EditText edtnames;
   EditText edtemail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edtnames=(EditText) findViewById(R.id.edtnames);
		edtemail=(EditText) findViewById(R.id.edtemail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void send_data(View v)
	{
		String names_input=edtnames.getText().toString();
		String email_input=edtemail.getText().toString();
		if(names_input!="" && email_input!="")
		{
			//send the data
			AsyncHttpClient client=new AsyncHttpClient();
			RequestParams params=new RequestParams();
			params.put("names", names_input);
			params.put("email", email_input);
			client.post("http://10.0.2.2/refresher/refresher.php", params,
					new AsyncHttpResponseHandler(){
				@Override
				public void onSuccess(int statusCode, String content) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),"Successfully Posted", 
							Toast.LENGTH_SHORT).show();
					super.onSuccess(statusCode, content);
				}
				@Override
				public void onFailure(int statusCode, Header[] headers,
						Throwable error, String content) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),"Failed To Post", 
							Toast.LENGTH_SHORT).show();				
					super.onFailure(statusCode, headers, error, content);
				}
				
				
			});
			
			
		}else
		{
		Toast.makeText(getApplicationContext(),"Empty Fields", 
				Toast.LENGTH_SHORT).show();
		}
	  	
	}
	
	
	
	
	
	
	
	
	
	
	

}
