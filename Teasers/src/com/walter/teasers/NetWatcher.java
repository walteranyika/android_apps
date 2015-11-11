package com.walter.teasers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWatcher extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
      ConnectivityManager cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo info=cm.getActiveNetworkInfo();
      DBController db=new DBController(context);
      if(info!=null)
      {
    	 if(info.isConnected() && db.dbSyncCount()>0)
    	 {
    		//start a service  
     		 Intent i=new Intent(context, MyService.class);
    		 context.stopService(i);
    	 }
    	 else
    	 {
    		//stop a service
    		 Intent i=new Intent(context, MyService.class);
    		 context.stopService(i);
    	 } 
      }
	}

}
