package com.walter.uniquefacts;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHandler2 extends SQLiteOpenHelper {
	//define database called 'satclass'
 private static final String MYDATABASE = "fav_u.db";
 private static final int VERSION = 1;//db version
 private final String SAMPLE_TABLE_NAME = "favor";
 protected Context context;
 public SQLiteHandler2(final Context connection) {
		//create database and set version
  super(connection, MYDATABASE, null, VERSION);
  this.context = connection;
  
 }
 //create table
 @Override
 public void onCreate(SQLiteDatabase db)
 {
	 db.execSQL("CREATE TABLE IF NOT EXISTS "+SAMPLE_TABLE_NAME +"(Id INTEGER PRIMARY KEY AUTOINCREMENT,Data VARCHAR);");	 	
 }
 
 //not required for now
 @Override
 public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
   //db.execSQL("DROP TABLE IF EXIST o");
//  onCreate(db);
 }
 //method to receive our 3 Strings
 public void Save(String data,String comment){
 
	 SQLiteDatabase db = this.getWritableDatabase();
	 //content values hold the collumns & their values
	 ContentValues c = new ContentValues();	 
	 c.put("Data", data);	 
	 long check = db.insert(SAMPLE_TABLE_NAME, null, c);
	 if(check !=0)
	 {
		//Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
	 }
	 else
	 {
		//Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
	 }	 	 
 }
 
      //this method retrieves data based on ID provided. takes in one parameter id
	 public ArrayList<String> getData()
	 {
	     //allow database to be read
		 SQLiteDatabase db = this.getReadableDatabase();
		 //use select query to search data in table - save found record in a cursor
		 final Cursor c = db.rawQuery("SELECT Data FROM " +	SAMPLE_TABLE_NAME +" ORDER BY Random() LIMIT 30", null);	 
		 //count number of rows returned by the cursor
		 int count = c.getCount();	 
		 //if its zero, no records found
		 ArrayList<String> list=new ArrayList<String>();
		   if(count == 0)
		   {
		    	//Toast.makeText(context, "Nothing  Found in the Database", Toast.LENGTH_SHORT).show();
		 		c.close();
		   }
		   else
		   {			  
		     int item=c.getColumnIndex("Data");
		     while(c.moveToNext())
		     {
		       list.add(c.getString(item));    	 
		     }
		     c.close();
		    }
	    //return a String with results
	     return list;	 
	}
	 public void delete(String data_to_delete)
	 {
		 SQLiteDatabase db = this.getWritableDatabase();
		 db.delete(SAMPLE_TABLE_NAME, "Data='"+data_to_delete+"'", null);		 
	 } 
}
 