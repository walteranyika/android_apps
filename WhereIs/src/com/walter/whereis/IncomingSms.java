package com.walter.whereis;


//import com.example.parsingdata.R;

//import com.example.parsingdata.R;

//import com.example.parsingdata.R;

import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {
    
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
     Context cont;
    public void onReceive(Context context, Intent intent) {
        cont=context;
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
 
        try {
             
            if (bundle != null) {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) 
                {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();                     
                    String senderNum = phoneNumber;
                    String sms = currentMessage.getDisplayMessageBody();     
                    process_sms(sms,senderNum);                  
                } 
              } 
 
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }   
    @SuppressWarnings("deprecation")
	public void process_sms(String sms,String phone)
    {
    	sms=sms.toLowerCase(Locale.ENGLISH);
		if(sms.contains("my parcel")|| sms.contains("parcel")|| sms.contains("luggage"))
    	{
    	  SharedPreferences pref=cont.getSharedPreferences("location", 0);
    	  String data=pref.getString("location", "Unknown Location");
    	  android.telephony.gsm.SmsManager smsmanager=android.telephony.gsm.SmsManager.getDefault();
    	  if(data.equals("Unknown Location"))
    	  {
    		smsmanager.sendTextMessage(phone, null, "Try again later. Location Unkown", null,null);  
    	  }
    	  else
    	  {
    		smsmanager.sendTextMessage(phone, null, data, null,null);    
    	  }
    	  
    	}
    	
    }
    
}