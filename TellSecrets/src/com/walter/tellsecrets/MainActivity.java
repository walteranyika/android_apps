package com.walter.tellsecrets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.WindowManager.BadTokenException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ListActivity {
   private static final int CUSTOM_DIALOG_ID = 0;
   CustomAdapter adapter;
   ArrayList<String> data;
   EditText edt=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Secrets");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#669900")));
		setContentView(R.layout.activity_main);
		
		if(isConnected()){
		new FetchData().execute("http://emobilis-server.com/walter/secrets/json.php");
		}else
		{
			Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_LONG).show();
		}
		registerForContextMenu(getListView());
	}
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			// TODO Auto-generated method stub
	      menu.setHeaderTitle("Secrets");
		  menu.add(0,v.getId(),0,"Share");
		  menu.add(0,v.getId(),0,"Save As Favourite");
		}
	@Override
	public boolean onContextItemSelected(android.view.MenuItem item)
		{
		AdapterView.AdapterContextMenuInfo info=(AdapterContextMenuInfo) item.getMenuInfo();
		int i=info.position;
		if(item.getTitle().equals("Share"))
		{
			Intent ii=new Intent();
			ii.setAction(Intent.ACTION_SEND);
			ii.setType("text/plain");
			ii.putExtra(Intent.EXTRA_SUBJECT, "Secrets:");
			ii.putExtra(Intent.EXTRA_TEXT, data.get(i));
			startActivity(Intent.createChooser(ii,"Share Via"));
		}else if(item.getTitle().equals("Save As Favourite"))
		{
			SQLiteHandler handle=new SQLiteHandler(getApplicationContext());
			handle.Save(data.get(i), "");
     		Toast.makeText(getApplicationContext(), "Added to Your Favourites", Toast.LENGTH_SHORT).show();
		}
			return true;
		}
    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
		    if(item.getItemId()==R.id.favourites)
		    {
		     Intent i=new Intent(this,SavedItems.class);
		     startActivity(i);
		    	
		    }else if(item.getItemId()==R.id.refresh)
		    {		    	
				if(isConnected())
				    {
					new FetchData().execute("http://emobilis-server.com/walter/secrets/json.php");
					try
					{
					adapter.notifyDataSetChanged();
					}catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();

					}
					}else
					{
						Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_LONG).show();
					}
		    	
		    }
		    else if(item.getItemId()==R.id.writenew)
		    {
		    showDialog(CUSTOM_DIALOG_ID);	
		    }
			else if(item.getTitle().equals("Help and About"))
			{
              Intent i=new Intent(this,Help_About.class);
              startActivity(i);
			}
			return super.onOptionsItemSelected(item);
		}
    private class FetchData extends AsyncTask<String, Void, ArrayList<String>>
	{
	  
	  JSONArray secretsarray=null;
	  ProgressDialog pDialog;
	  
	  @Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			//super.onPreExecute();
		  Log.d("Json", "Started");
		  pDialog=new ProgressDialog(MainActivity.this);
		  pDialog.setMessage("Fetching Secrets");
		  //pDialog.setTitle("progress");		  
		  pDialog.show();
		}
		@Override
		protected ArrayList<String> doInBackground(String... url) {
			 
			data=new ArrayList<String>();
			String secrets=GET(url[0]);
			Log.d("Json", secrets);
			try {
				JSONObject object=new JSONObject(secrets);
				secretsarray=object.getJSONArray("secrets");
				for (int i = 0; i < secretsarray.length()-1; i++)
				{
				  JSONObject ob=secretsarray.getJSONObject(i);
				  data.add(ob.getString("name"));
				  Log.d("Item", ob.getString("name"));
				}
			} catch (JSONException e) 
			{
				Log.d("Item", "Error in Json");
				
				e.printStackTrace();
			}
			
			// TODO Auto-generated method stub
			return data;
		}
		@Override
		protected void onPostExecute(ArrayList<String> result) {
			// TODO Auto-generated method stub
		  Log.d("Item", "Loading to the List");
		  pDialog.dismiss();
	      adapter=new CustomAdapter(MainActivity.this, result);
	      setListAdapter(adapter);
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
    public class SendData extends AsyncTask<String, Void, Void>{
  	  ProgressDialog pDialog;
	  
  	  @Override
  		protected void onPreExecute() {
  			// TODO Auto-generated method stub
  			//super.onPreExecute();
  		  Log.d("Json", "Started");
  		  pDialog=new ProgressDialog(MainActivity.this);
  		  pDialog.setMessage("Posting");
  		 // pDialog.setTitle("progress");		  
  		  pDialog.show();
  		}
		@Override
		protected Void doInBackground(String... arg0) {
			HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://emobilis-server.com/walter/secrets/insert.php");
            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("secrets", arg0[0]));    
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                try {
                    httpclient.execute(httppost);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // Execute HTTP Post Request
                ResponseHandler<String> responseHandler=new BasicResponseHandler();
                String responseBody = httpclient.execute(httppost, responseHandler);
                Log.i("HTTP Success",responseBody);                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.i("HTTP Failed", e.toString());
            }              
			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) 
		{
			pDialog.dismiss();
			Toast.makeText(getApplication(), "Posted Successfully", Toast.LENGTH_SHORT).show();
		}
    	
    }
    private Dialog createExampleDialog() {
		 
        AlertDialog.Builder builder = new AlertDialog.Builder(this);        
        builder.setTitle("Post A Secret");
        builder.setMessage("Enter A Secret to Post"); 
         // Use an EditText view to get user input.
         final EditText input = new EditText(this);         
         input.setId(123);
         builder.setView(input);
 
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
 
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if(isConnected())
                {
                 new SendData().execute(value);//handle.Save(value, tv.getText().toString());
                 
                }else
                {
        		 Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_LONG).show();
                }
                return;
            }
        });
 
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
 
        return builder.create();
    }
    protected android.app.Dialog onCreateDialog(int id)
    {
    	
    	switch(id)
    	{
    	case CUSTOM_DIALOG_ID:
    		return createExampleDialog();    		
    	default:
    	    return null;
    	}
		
	};
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
