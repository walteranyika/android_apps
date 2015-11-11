package com.walter.whereis;

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
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class LocationReceiver extends BroadcastReceiver implements LocationListener {
	LocationManager locationManager;
	Context context;
	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		locationManager = (LocationManager)  context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000*60, 0, this);
	   this.context=context;
	}

	@Override
	public void onLocationChanged(Location location ) {
		// TODO Auto-generated method stub
		String addressline = "";
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if(addresses.size() ==  1)
            {
                Address address = addresses.get(0);
                String aline = "";
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                {
                    aline = aline + address.getAddressLine(i) + " ";
                }
                addressline = " along " + aline ;
                SharedPreferences prefs=context.getSharedPreferences("location", 0);
                Editor editor=prefs.edit();
                if(!aline.equals(""))
                {
                    editor.putString("location",aline );
                }else
                {
                	editor.putString("location", "Unknown Location");	
                }
                editor.commit();
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
		
        File log=
                new File(Environment.getExternalStorageDirectory(),
                         "LocationLog2.txt");

        try {
              BufferedWriter out=  new BufferedWriter(new FileWriter(log.getAbsolutePath(), log.exists()));
              out.write(new Date().toString());
              out.write(" : ");              
             
              out.write(addressline);
              out.write("\n");
              out.close();
            }
            catch (IOException e) {
              Log.e(getClass().getName(), "Exception appending to log file", e);
            }
        
		
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
