package com.walter.currencyconveter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;



import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager.BadTokenException;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity {

	TextView tv_from,tv_to;
	EditText edt_from,edt_to,edt_search,edt_exchange_rate;
	
	double exchange_rate;
	String to_code;
	String to_country;	
	String from_code;
	String from_country;
	double xrated=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Exchange");
	    // Enabling Up / Back navigation
	    actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9933cc")));
		tv_from=(TextView) findViewById(R.id.tv_from);
		tv_to=(TextView) findViewById(R.id.tv_to);
		edt_exchange_rate=(EditText) findViewById(R.id.edt_exchange_rate);
		//edt_exchange_rate.setEnabled(false);
		edt_from=(EditText) findViewById(R.id.edt_from);
		edt_to=(EditText) findViewById(R.id.edt_to);
		SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
		to_code=sharedprefs.getString("to_code", "USD");
		from_code=sharedprefs.getString("from_code", "USD");
		exchange_rate=Double.valueOf(sharedprefs.getString("xrate", "0.00"));
		tv_from.setText(from_code);
		tv_to.setText(to_code);
		//http://members.000webhost.com/cpanel.php?accountID=16082519&login_hash=lzKdzGTIwMgfox09
		edt_from.addTextChangedListener(new TextWatcher() {			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				if(edt_from.getText().toString()!="")
				{
				 double x=0;
				 try{
				      x=Double.parseDouble(edt_from.getText().toString().trim());
				    }catch (NumberFormatException e) {
					x=1;// TODO: handle exception
				   }
				 double xx=x*xrated;
	         	 DecimalFormat dForm = new DecimalFormat("#.#######"); 
	         	 String yyy=dForm.format(xx);
				 edt_to.setText(yyy);
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	   @Override
	public void onBackPressed() {
//	    finishActivity(1010);
//	    finishActivity(1020);
	       android.os.Process.killProcess(android.os.Process.myPid());
	       // This above line close correctly
	   }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getTitle().equals("Refresh"))
		{
		 from_code=from_code.trim();
		 to_code=to_code.trim();
		// String url=String.format("http://rate-exchange.appspot.com/currency?from=%s&to=%s&q=1",from_code,to_code);
		 String url=String.format("http://emobilis-server.com/walter/secrets/pesa.php?from=%s&to=%s",from_code,to_code);
		 //http://emobilis-server.com/walter/secrets/pesa.php?from=%s&&to=%s
		 Log.d("test", url);
		 if(isConnected())
		 {
		 new FetchDataLatest().execute(new String[]{url});
		 }
		 else
		 {
			 Toast.makeText(getApplicationContext(), "Check your internet settings", Toast.LENGTH_LONG).show();
		 }
		}
		else if(item.getTitle().equals("Help and About"))
		{
            Intent i=new Intent(this,Help_About.class);
            startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
	public void btn_to_click(View v)
	{
		 Intent i=new Intent(MainActivity.this,ToActivity.class);
		 startActivity(i);
		// startActivityForResult(i, 1010);
	}
	public void btn_from_click(View v)
	{
		 Intent i=new Intent(MainActivity.this,FromActivity.class);
		 startActivity(i);	
		 //startActivityForResult(i, 1020);
	}
	public void btn_switch_click(View v)
	{

		
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
	            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	            if (networkInfo != null && networkInfo.isConnected())
	                return true;
	            else
	                return false;  
	    }
    private class FetchDataLatest extends AsyncTask<String, Void, String>
    {
    	ProgressDialog progressDialog;
    	
		       @Override
		protected void onPreExecute() {
		    if (progressDialog == null) {
		        progressDialog = createProgressDialog(MainActivity.this);
		        progressDialog.show();
		        } else 
		        {
		         progressDialog.show();
		        }
		    	super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String data=GET(arg0[0]);
			String result="";
			Log.d("test", data);
			if(!data.contains("err"))
			{
				//http://rate-exchange.appspot.com/currency?from=USD&to=GBP&q=1
				//{"to": "GBP", "rate": 0.59958999999999996, "from": "USD", "v": 0.59958999999999996}
			  try {
				JSONObject job=new JSONObject(data);
		         	double rate=job.getDouble("rate");
		         	xrated=rate;
		         	 DecimalFormat dForm = new DecimalFormat("#.#######"); 
		         	 result=dForm.format(rate);
			    } catch (JSONException e) 
			    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }	
			}
			return result;
		}
    	@Override
    protected void onPostExecute(String result) {
    		// TODO Auto-generated method stub
    		//edt_from.setText("1");
    		edt_exchange_rate.setText(result);
    		//edt_to.setText(result);
    		Log.d("X Rate", result);
    		//
    		try
    		{
    		  double r=Double.parseDouble(result);
    		  double rr=Double.parseDouble(edt_from.getText().toString());
    		  double res=r*rr;
	         	 DecimalFormat dForm = new DecimalFormat("#.#######"); 
	         	String ress=dForm.format(res);
    		  edt_to.setText(ress);
    		 // edt_exchange_rate.setText("1345");
    		}
    		catch (Exception e)
    		{				// TODO: handle exception
    			edt_to.setText("Try Again");
    			Log.d("Error", result);
			}    		
    		progressDialog.dismiss();
    		super.onPostExecute(result);
    	}
    }
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		   SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
		   Editor editor=sharedprefs.edit();
		   editor.putString("to_value", edt_to.getText().toString());
		   editor.putString("from_value", edt_from.getText().toString());
		   editor.putString("xrate", String.valueOf(exchange_rate));
		   editor.commit();
		super.onPause();
	}
     @Override
	protected void onResume() {
		// TODO Auto-generated method stub
	   SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
		to_code=sharedprefs.getString("to_code", "USD");
		from_code=sharedprefs.getString("from_code", "USD");
		tv_from.setText(from_code);
		tv_to.setText(to_code);
		edt_from.setText(sharedprefs.getString("from_value", ""));
		edt_to.setText(sharedprefs.getString("to_value", ""));
		try{
		exchange_rate=Double.valueOf(sharedprefs.getString("xrate", "0.00"));
		}catch (NumberFormatException e) {
			exchange_rate=0.0;// TODO: handle exception
		}
		super.onResume();
	}
	 @Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
		to_code=sharedprefs.getString("to_code", "USD");
		from_code=sharedprefs.getString("from_code", "USD");
		tv_from.setText(from_code);
		tv_to.setText(to_code);
		edt_from.setText(sharedprefs.getString("from_value", ""));
		edt_to.setText(sharedprefs.getString("to_value", ""));
		exchange_rate=Double.valueOf(sharedprefs.getString("xrate", "0.00"));
		super.onRestart();
	}
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
		   SharedPreferences sharedprefs=getSharedPreferences("prefs", MODE_PRIVATE);
		   Editor editor=sharedprefs.edit();
		   editor.putString("to_value", edt_to.getText().toString());
		   editor.putString("from_value", edt_from.getText().toString());
		   editor.putString("xrate", String.valueOf(exchange_rate));
		   
		   editor.commit();
    	   super.onDestroy();
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
