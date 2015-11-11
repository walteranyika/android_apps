package com.walter.mpesaanalyzer;
import java.util.ArrayList;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHandler extends SQLiteOpenHelper {
	//define database called 'satclass'
 private static final String MYDATABASE = "xtian.db";
 private static final int VERSION = 1;//db version
 private final String TABLE_NAME = "trainees";
 protected Context context;
 public SQLiteHandler(final Context connection) {
		//create database and set version
  super(connection, MYDATABASE, null, VERSION);
  this.context = connection;
  
 }
 //create table
 @Override
 public void onCreate(SQLiteDatabase db)
 {
	 db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME +"(Id INTEGER PRIMARY KEY AUTOINCREMENT,transaction VARCHAR,month VARCHAR,type VARCHAR,INTEGER amount,);");	 	
 }
  //transaction,month,amount
 //not required for now
 @Override
 public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
   //db.execSQL("DROP TABLE IF EXIST o");
//  onCreate(db);
 }
 //method to receive our 3 Strings
 public void Save(String transaction,String month,String type,int amount )
 {
	 SQLiteDatabase db = this.getWritableDatabase();
	 //content values hold the collumns & their values
	 if(type.equals("Income"))
	 {
		 ContentValues c = new ContentValues();	 
		 c.put("transaction", transaction);
		 c.put("month", month);
		 c.put("type", "Income");
		 c.put("amount", amount);
		 long check = db.insert(TABLE_NAME, null, c);
	 }
	 else if(type.equals("Expenditure"))
	 {
		 ContentValues c = new ContentValues();	 
		 c.put("transaction", transaction);
		 c.put("month", month);
		 c.put("type", "Expenditure");
		 c.put("amount", amount);
		 long check = db.insert(TABLE_NAME, null, c);
	 }
 }
 
      //this method retrieves data based on ID provided. takes in one parameter id
     //transaction,month,amount
	 public ArrayList<Integer> getData_month(String search_term)
	 {
	     //allow database to be read
		 SQLiteDatabase db = this.getReadableDatabase();
		 //use select query to search data in table - save found record in a cursor
		 //"SELECT * FROM " +	SAMPLE_TABLE_NAME +" ORDER BY Random() LIMIT 15"
		 final Cursor c = db.rawQuery("SELECT * FROM " +	TABLE_NAME +" WHERE month LIKE '%"+search_term+"%'", null);	 
		 //count number of rows returned by the cursor
		 int count = c.getCount();	 
		 //if its zero, no records found
		 ArrayList<Integer> list=new ArrayList<Integer>();
		   if(count == 0)
		   {
		    	//Toast.makeText(context, "Nothing  Found in the Database", Toast.LENGTH_SHORT).show();
		 		c.close();
		   }
		   else
		   {			  
		     //int transaction=c.getColumnIndex("transaction");
		     //int month=c.getColumnIndex("month");
		     int amount=c.getColumnIndex("amount");
		     //MyQuotes ob=new MyQuotes();
		     while(c.moveToNext())
		     {
		    	 //list.add(c.getString(preacher));
		    	 list.add(c.getInt(amount));
			    
		     }
		     c.close();
		    }
	    //return a String with results
	     return list;	 
	}
	/* public ArrayList<MyQuotes> getDataa()
	 {
	     //allow database to be read
		 SQLiteDatabase db = this.getReadableDatabase();
		 //use select query to search data in table - save found record in a cursor
		 final Cursor c = db.rawQuery("SELECT * FROM " +	SAMPLE_TABLE_NAME +" ORDER BY Random() LIMIT 30", null);	 
		 //count number of rows returned by the cursor
		 int count = c.getCount();	 
		 //if its zero, no records found
		 ArrayList<MyQuotes> list=new ArrayList<MyQuotes>();
		   if(count == 0)
		   {
		    	//Toast.makeText(context, "Nothing  Found in the Database", Toast.LENGTH_SHORT).show();
		 		c.close();
		   }
		   else
		   {			  
		     int quote=c.getColumnIndex("Data");
		     int preacher=c.getColumnIndex("Comment");
		     ;
		     while(c.moveToNext())
		     {
		    	  MyQuotes ob=new MyQuotes();
			      ob.setPreacher(c.getString(preacher));
			      ob.setQuote(c.getString(quote));
			      list.add(ob);
			     Log.d("dddd", ob._preacher);
		     }
		     c.close();
		    }
	    //return a String with results
	     return list;	 
	}
	 public ArrayList<MyQuotes> search(String search_term)
	 {
	     //allow database to be read
		 SQLiteDatabase db = this.getReadableDatabase();
		 //use select query to search data in table - save found record in a cursor
		 final Cursor c = db.rawQuery("SELECT * FROM " +	SAMPLE_TABLE_NAME +" WHERE Data LIKE '%"+search_term+"%'  LIMIT 15", null);	 
		 //count number of rows returned by the cursor
		 int count = c.getCount();	 
		 //if its zero, no records found
		 ArrayList<MyQuotes> list=new ArrayList<MyQuotes>();
		   if(count == 0)
		   {
		    	//Toast.makeText(context, "Nothing  Found in the Database", Toast.LENGTH_SHORT).show();
		 		c.close();
		   }
		   else
		   {			  
			     int quote=c.getColumnIndex("Data");
			     int preacher=c.getColumnIndex("Comment");
			    
			     while(c.moveToNext())
			     {
			    	 MyQuotes ob=new MyQuotes(); 
			    	 ob.setPreacher(c.getString(preacher));
				      ob.setQuote(c.getString(quote));
				      list.add(ob);
			     }
		     c.close();
		    }
	    //return a String with results
	     return list;		 
		 
	 }*/
}
 