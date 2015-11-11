package com.walter.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager.BadTokenException;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView tv;
    EditText edt;
    int count=0;
    String data_r="";
    Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv=(TextView) findViewById(R.id.tvOne);
		edt=(EditText) findViewById(R.id.edtOne);
	    btn=(Button) findViewById(R.id.button1);
		if(isConnected())
		{
			new SendData().execute("");
			
		}else
		{
			Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void btn_clicked(View v)
	{
		String temp=edt.getText().toString();
		if(count==0)
		{
		  data_r=temp;
		  edt.setText("");
		  count++;
		}
		else if(count==1)
		{
			  data_r=data_r+"*"+temp;
			  count++;
			  edt.setText("");
		}else if(count==2)
		{
			  data_r=data_r+"*"+temp;
			  count++;			  
		}
		Log.i("User Data at ", count+" "+data_r);
		if(isConnected())
		{
			if(!data_r.equals(""))
			{
			  new SendData().execute(data_r);
			  if(count==3)
			  {
				btn.setVisibility(View.GONE);  
				edt.setVisibility(View.GONE);
			  }
			}else
			{
		      Toast.makeText(getApplicationContext(), "Empty String", Toast.LENGTH_LONG).show();	
			}
		}else
		{
			Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_LONG).show();
		}
		
		
	}

	public static String GET(String url){
	        InputStream inputStream = null;
	        String result = "";
	        try {
	 
	            // create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	 
	            // make GET request to the given URL
	            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
	 
	            // receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            // convert inputstream to string
	            if(inputStream != null)
	                result = convertInputStreamToString(inputStream);
	            else
	                result = "none";
	 
	        } catch (Exception e) {
	            Log.d("InputStream", e.getLocalizedMessage());
	        }
	 
	        return result;
	    }
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
	        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	        String line = "";
	        String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	 
	        inputStream.close();
	        return result;
	 
	    }
	public boolean isConnected(){
	            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
	            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	            if (networkInfo != null && networkInfo.isConnected())
	                return true;
	            else
	                return false;  
	    }
	public class SendData extends AsyncTask<String, Void, String>{
			  ProgressDialog pDialog;	  
			  @Override
			 protected void onPreExecute() {
					// TODO Auto-generated method stub
				//super.onPreExecute();
			  Log.d("Json", "Started");
			  pDialog=new ProgressDialog(MainActivity.this);
			  pDialog.setMessage("Requesting USSD Code");
			 // pDialog.setTitle("progress");		  
			  pDialog.show();
			}
			@Override
			protected String doInBackground(String... arg0) {
				String result="";
				//String text=edt.getText().toString();
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://inspire-africa.org/ussd/kevo.php");
			    try {
			        // Add your data
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			        Random r = new Random();
			        int i1 = r.nextInt(800000 - 500000) + 300000;
			        
			        nameValuePairs.add(new BasicNameValuePair("phoneNumber", "0723"+String.valueOf(i1))); 
			        nameValuePairs.add(new BasicNameValuePair("text", arg0[0]));
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			       /* try {
			           // httpclient.execute(httppost);
			        } catch (UnsupportedEncodingException e) {
			            e.printStackTrace();
			        }*/
			        // Execute HTTP Post Request
			        ResponseHandler<String> responseHandler=new BasicResponseHandler();
			        String responseBody = httpclient.execute(httppost, responseHandler);
			        result=responseBody;
			        Log.i("HTTP Success",responseBody);                
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			        Log.i("HTTP Failed", e.toString());
			    }              
				
				return result;
			}
			@Override
			protected void onPostExecute(String result) 
			{
				pDialog.dismiss();
				//Toast.makeText(getApplication(), "Posted Successfully "+result, Toast.LENGTH_SHORT).show();
				tv.setText(result);
			}
			
		}
	public static ProgressDialog createProgressDialog(Context mContext) {
	    ProgressDialog dialog = new ProgressDialog(mContext);
	    try {
	            dialog.show();
	    } catch (BadTokenException e) {
	
	    }
	    dialog.setCancelable(false);
	    dialog.setContentView(R.layout.progressdialog);
	    // dialog.setMessage(Message);
	        return dialog;
	  }


}
