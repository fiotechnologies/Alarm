package com.fio.alarm;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditAlarmlocation extends Activity{
	EditText alarmName;
	DBController controller = new DBController(this);
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
			setContentView(R.layout.innerlocation);
			
			alarmName = (EditText) findViewById(R.id.alarmName);
			Intent objIntent = getIntent();
			String alarmId = objIntent.getStringExtra("alarmId");
			Log.d("Reading: ", "Reading all contacts..");
			HashMap<String, String> alarmList = controller.getAlarmInfo(alarmId);
			Log.d("alarmName",alarmList.get("alarmName"));
			if(alarmList.size()!=0) {
			alarmName.setText(alarmList.get("alarmName"));
			}
	    }
	public void editAlarm(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		alarmName = (EditText) findViewById(R.id.alarmName);
		Intent objIntent = getIntent();
		String alarmId = objIntent.getStringExtra("alarmId");
		queryValues.put("alarmId", alarmId);
		queryValues.put("alarmName", alarmName.getText().toString());
		
		controller.updateAlarm(queryValues);
		this.callHomeActivity(view);
		
	}
	public void removeAlarm(View view) {
		Intent objIntent = getIntent();
		String alarmId = objIntent.getStringExtra("alarmId");
		controller.deleteAlarm(alarmId);
		this.callHomeActivity(view);
		
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), Newalarm.class);
		startActivity(objIntent);
	}
}

