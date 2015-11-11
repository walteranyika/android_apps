package com.walter.mpesaanalyzer;


//import com.example.parsingdata.R;

//import com.example.parsingdata.R;

//import com.example.parsingdata.R;

import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {
    
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
     
    public void onReceive(Context context, Intent intent) {
     
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
 
        try {
             
            if (bundle != null) {
                 
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();                     
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
     
                                       
                } 
              } 
 
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }   
    public void process_sms(String sms)
    {
    	sms=sms.toLowerCase(Locale.ENGLISH);
		if(sms.contains("new m-pesa balance") && sms.contains("confirmed"))
    	{
    	     //received from banks and mshwari 	

       	  
    	  
		     //repaid a loan
    		
			
			
    	    //sent/transferred  monies //pay bill sent to
    		if(sms.contains("sent to"))
    		{
    		 int x= sms.indexOf("Ksh")+1;	
    		 int y=sms.indexOf("sent");    		
    		 String money_out=sms.substring(x+2, y-1);
    		}
			 //deposits agent
    		if(sms.contains("Give Ksh"))
    		{
    		 int x= sms.indexOf("Ksh")+1;	
    		 int y=sms.indexOf("cash");    			
    		 String money_in=sms.substring(x+2, y-1);
    		}    		
			 //bought item
    		if(sms.contains("you bought"))
    		{
    		 int x= sms.indexOf("ksh")+1;	
    		 int y=sms.indexOf("of");    			
    		 String money_out=sms.substring(x+2, y-1);
    		}
    		
			//withdrawn monies bank
    		if(sms.contains("withdrawn"))
    		{
    		 int x= sms.indexOf("ksh")+1;	
    		 int y=sms.indexOf("withdrawn");    			
    		 String money_out=sms.substring(x+2, y-1);
    		}  		
    	
			//withdraw money from agents
    		if(sms.contains("withdraw"))
    		{
    		 int x= sms.indexOf("ksh")+1;	
    		 int y=sms.indexOf("from");    			
    		 String money_out=sms.substring(x+2, y-1);
    		}
			//received
    		if(sms.contains("received"))
    		{
    		 int x= sms.indexOf("ksh")+1;	
    		 int y=sms.indexOf("from");    			
    		 String money_in=sms.substring(x+2, y-1);
    		}
    	     	
    	     	
    	}
    	
    }
    
}