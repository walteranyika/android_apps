package com.walter.teasers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) 
	{
	  Bundle bundle=intent.getExtras();
	  Object[] pdus=(Object[]) bundle.get("pdus");
	  SmsMessage message=SmsMessage.createFromPdu((byte[])pdus[0]);
	  String message_body=message.getDisplayMessageBody();
	  String messge_from_address=message.getDisplayOriginatingAddress();	  
      DBController  dbController=new DBController(context);
      Date tarehe=new Date();
      String now=new SimpleDateFormat("yyyy/MM/dd_HHmm",Locale.US).format(tarehe);
      dbController.insertUser(messge_from_address, message_body, now);
	}

}