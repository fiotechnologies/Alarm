package com.fio.alarm;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {
	private static final String LOGCAT = null;

	public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
        Log.d(LOGCAT,"Created");
    }
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		query = "CREATE TABLE alarmlocation ( alarmId INTEGER PRIMARY KEY AUTOINCREMENT, alarmName TEXT)";
        database.execSQL(query);
        Log.d(LOGCAT,"alarm Created");
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS alarmlocation";
		database.execSQL(query);
        onCreate(database);
	}
	
	public void insertAlarm(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("alarmName", queryValues.get("alarmName"));
		database.insert("alarmlocation", null, values);
		database.close();
	}
	
	public int updateAlarm(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put("alarmName", queryValues.get("alarmName"));
	    return database.update("alarmlocation", values, "alarmId" + " = ?", new String[] { queryValues.get("alarmId") });
	    //String updateQuery = "Update  words set txtWord='"+word+"' where txtWord='"+ oldWord +"'";
	    //Log.d(LOGCAT,updateQuery);
	    //database.rawQuery(updateQuery, null);
	    //return database.update("words", values, "txtWord  = ?", new String[] { word });
	}
	
	public void deleteAlarm(String id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();	 
		String deleteQuery = "DELETE FROM  alarmlocation where alarmId='"+ id +"'";
		Log.d("query",deleteQuery);		
		database.execSQL(deleteQuery);
	}
	
	public ArrayList<HashMap<String, String>> getAllAlarm() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM alarmlocation";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("alarmId", cursor.getString(0));
	        	map.put("alarmName", cursor.getString(1));
                wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return wordList;
	}
	
	public HashMap<String, String> getAlarmInfo(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM alarmlocation where alarmId='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
	        do {
					//HashMap<String, String> map = new HashMap<String, String>();
	        	wordList.put("alarmName", cursor.getString(1));
				   //wordList.add(map);
	        } while (cursor.moveToNext());
	    }				    
	return wordList;
	}	
}