package com.walter.teasers;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MyService extends Service {
    @Override
	public int onStartCommand(Intent intent, int flags, int startId) {
    	final DBController db=new DBController(this);
    	if(db.dbSyncCount()>0)
    	{
    	   String json=db.composeJSONfromSQLite();
    	   AsyncHttpClient client = new AsyncHttpClient();
           RequestParams params = new RequestParams();
           params.put("smsJSON", json);
           client.post("inspire-africa.org/smsync.php", params, new AsyncHttpResponseHandler()
           {
	        	@Override
	        	public void onSuccess(int statusCode, String content) 
	        	{
	               //update the db with returned results
	        		 /* try {
                       JSONArray arr = new JSONArray(content);
                        for(int i=0; i<arr.length();i++)
                        {
                            //JSONObject obj = (JSONObject)arr.get(i);
                            //db.updateSyncStatus(obj.get("id").toString(),obj.get("status").toString());
                        }
                        
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                    	Log.e("Error_Other", "Error Occured [Server's JSON response might be invalid]!");
                        e.printStackTrace();
                    }	*/   
	        		Log.d("Error_Other", "DB Sync completed!");
	        	}
	        	@Override
	        	public void onFailure(int statusCode, Throwable error,String content) 
	        	{
                    if(statusCode == 404){
                       Log.d("Error_404", "Requested resource not found");
                    }else if(statusCode == 500){
                    	Log.d("Error_500","Something went wrong at server end");
                    }else{
                    	Log.d("Error_Other", "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]");
                    }
	       
	        	}        	   
           });
           this.stopSelf();	
    	}else
    	{
    	  
    		this.stopSelf();	
    	}
    	
    	
      	return Service.START_NOT_STICKY;      	
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
