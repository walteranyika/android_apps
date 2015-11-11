package com.walter.mpesaanalyzer;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class Reports extends Activity {
	   @Override
	  protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
	  }
	  public void load_whole_year_report(View v)	  
	  {
		  String[] month_name={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"}; 
		 for(int i=0;i<12;i++)
		 {
		   //new Fetch_data().execute(params) 
		 } 
		  
	  }
	  public static String getMonth(int month)
	   {
		String[] month_name={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC",};   
		return month_name[month];
	   }
	  private class Fetch_data extends AsyncTask<String, Void, Integer>
	   {
			   @Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}
			@Override
			protected Integer doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				//FETCH FOR A CERTAIN MONTH AND ADD THEM UP
				SQLiteHandler handler=new SQLiteHandler(getApplicationContext());
				ArrayList<Integer> data=new ArrayList<Integer>();
				data=handler.getData_month(arg0[0]);
				int size=data.size();
				 int total=0;
				if(size>0)
				{
				 	for(int i=0;i<size;i++)
				 	{
				 	  total=total+data.get(i);	
				 	}				
				}
				return total;
			}
			@Override
			protected void onPostExecute(Integer result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
			}		   
	   }
}
