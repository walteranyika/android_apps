package com.inspireafrica.chatter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ChatterApplication extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
				 
		Parse.initialize(this, "IilfNbscszzSkEg5azVHnphL8YohbyRMFiNzTWFH", "efDMnjSLpTooBt96AK6pos2gOktsb43nGRMzWyqu");
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
        testObject.saveInBackground();
	
	}

}
