/***
  Copyright (c) 2010 CommonsWare, LLC
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package com.commonsware.cwac.locpoll.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import com.commonsware.cwac.locpoll.LocationPoller;

public class LocationReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    File log=
        new File(Environment.getExternalStorageDirectory(),
                 "LocationLog.txt");

    try {
      BufferedWriter out=
          new BufferedWriter(new FileWriter(log.getAbsolutePath(),
                                            log.exists()));

      out.write(new Date().toString());
      out.write(" : ");
      
      Bundle b=intent.getExtras();
      Location loc=(Location)b.get(LocationPoller.EXTRA_LOCATION);
      String msg;

      String addressline=":Received";
		//location=locationManger.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

      if (loc==null) {
        loc=(Location)b.get(LocationPoller.EXTRA_LASTKNOWN);

        if (loc==null) {
          msg=intent.getStringExtra(LocationPoller.EXTRA_ERROR);
        }
        else {
          msg="TIMEOUT, lastKnown="+loc.toString();
        }
      }
      else {
        msg=loc.toString();
      }

      if (msg==null) {
        msg="Invalid broadcast received!";
      }
		Geocoder geocoder=new Geocoder(context, Locale.getDefault());
		try {
			List<Address>addresses=geocoder.getFromLocation(loc.getAltitude(), loc.getLongitude(), 1);
		    if(addresses.size()==1)
		    {
		    	Address add=addresses.get(0);
		    	String aline="";
		    	for(int i=0;i<add.getMaxAddressLineIndex();i++)
		    	{
		    	  	aline=aline+add.getAddressLine(i)+"  ";
		    	}
		    	addressline="Your item is At  "+aline;
		    }
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   
  out.write(addressline);
    

     // out.write(msg);
      out.write("\n");
      out.close();
    }
    catch (IOException e) {
      Log.e(getClass().getName(), "Exception appending to log file", e);
    }
  }
}