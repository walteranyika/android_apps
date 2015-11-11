package com.walter.datacolletor;

import java.util.ArrayList;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class DBController  extends SQLiteOpenHelper {
 
    public DBController(Context applicationcontext) {
        super(applicationcontext, "datacollectors.db", null, 1);
    }
    //Creates Table
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE UsersTable ( userId INTEGER PRIMARY KEY, userNames TEXT, userEmail TEXT, userPhone TEXT, userAddress TEXT, udpateStatus TEXT, guid TEXT)";
        database.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS UsersTable ";
        database.execSQL(query);
        onCreate(database);
    }
    /**
     * Inserts UsersTable  into SQLite DB
     * @param queryValues
     */
    public void insertUser(String names,String email,String phone,String location) 
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        String  uniqueID = UUID.randomUUID().toString();        
        values.put("userNames", names);
        values.put("userEmail", email);
        values.put("userPhone", phone);
        values.put("userAddress", location);
        values.put("udpateStatus", "no");
        values.put("guid", uniqueID);
        database.insert("UsersTable", null, values);
        database.close();
    }
 
    /**
     * Get list of User from SQLite DB as Array List
     * @return
     */
    public ArrayList<User> getAllUser() 
    {
        ArrayList<User> wordList;
        wordList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM UsersTable";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) 
        {
            do {
            	User data=new User(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(6));
                wordList.add(data);
               } 
            while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }
 
    /**
     * Compose JSON out of SQLite records
     * @return
     */
    public String composeJSONfromSQLite()
    {
        ArrayList<User> wordList;
        wordList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM UsersTable where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) 
		{
            do {
            	User data=new User(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(6));
                wordList.add(data);
               } 
            while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        //Use GSON to serialize Array List to JSON       
        return gson.toJson(wordList);
    }
 
    /**
     * Get Sync status of SQLite
     * @return
     */
    public String getSyncStatus()
    {
        String msg = null;
        if(this.dbSyncCount() == 0)
        {
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        }else{
            msg = "DB Sync needed\n";
        }
        return msg;
    }
 
    /**
     * Get SQLite records that are yet to be Synced
     * @return
     */
    public int dbSyncCount()
    {
        int count = 0;
        String selectQuery = "SELECT  * FROM UsersTable where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }
 
    /**
     * Update Sync status against each User ID
     * @param id
     * @param status
     */
    public void updateSyncStatus(String id, String status)
    {
        SQLiteDatabase database = this.getWritableDatabase();    
        String updateQuery = "Update UsersTable set udpateStatus = '"+ status +"' where userId="+"'"+ id +"'";
        Log.d("query",updateQuery);       
        database.execSQL(updateQuery);
        database.close();
    }
}