package com.fio.alarm;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.fio.alarm.R;

public class Newalarm extends ListActivity {
	Intent intent;
	TextView alarmId;
	DBController controller = new DBController(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savedinnerlocation);
		
		ArrayList<HashMap<String, String>> alarmList = controller.getAllAlarm();
		if (alarmList.size() != 0) {
			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					alarmId = (TextView) view.findViewById(R.id.alarmId);
					String valAlarmId = alarmId.getText().toString();
					Intent objIndent = new Intent(getApplicationContext(),
							EditAlarmlocation.class);
					objIndent.putExtra("alarmId", valAlarmId);
					startActivity(objIndent);
				}
			});
			ListAdapter adapter = new SimpleAdapter(Newalarm.this, alarmList,
					R.layout.view_alarm, new String[] { "alarmId",
							"alarmName" }, new int[] { R.id.alarmId,
							R.id.alarmName });
			setListAdapter(adapter);
		}
	}

	public void showAddForm(View view) {
		Log.v("activity", "innerlocation");
		Intent objIntent = new Intent(getApplicationContext(),
				innerlocation.class);
		Log.v("activity2", "innerlocation2");

		startActivity(objIntent);
	}
}
