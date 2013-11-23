package com.fio.alarm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fio.alarm.R;

public class innerlocation extends Activity implements LocationListener {


	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected Context context;
	
	EditText edittext2;
	EditText alarmName;
	String provider;
	protected String latitude, longitude;
	TextView alarmId;
	DBController controller = new DBController(this);
	Ringtone rt;
	RingtoneManager mRingtoneManager;
    String s = null;
	String s1 = null;
	EditText  edittext ;
	CheckBox ringbox   ;
	CheckBox vibratebox ;
	CheckBox rvbox ; 	 
	MediaPlayer mp;
	Vibrator v1;
	Button stopbutton;





	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Log.v("activity3", "innerlocation3");

		setContentView(R.layout.innerlocation);
		//Log.v("activity4", "innerlocation4");

		alarmName = (EditText) findViewById(R.id.alarmName);
		
		//Log.v("activity5", "innerlocation5");
		 stopbutton=(Button)findViewById(R.id.button2);
		 edittext   = (EditText) findViewById(R.id.editText1);
		 
		  mp = MediaPlayer.create(this, R.raw.alarm);

	      v1 = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		  LocationManager locationManager =(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1,
		  this);
		  
		
	}

	public void addNewAlarm(View view) {
		HashMap<String, String> queryValues = new HashMap<String, String>();
		queryValues.put("alarmName", alarmName.getText().toString());
		controller.insertAlarm(queryValues);
		this.callHomeActivity(view);
	}

	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), Newalarm.class);
		startActivity(objIntent);
	}

	@Override
	public void onLocationChanged(Location loc) {

		longitude = "Longitude: " + loc.getLongitude();
		latitude = "Latitude: " + loc.getLatitude();

		/*----------to get City-Name from coordinates ------------- */

		String Area = null;
		String Area1 = null;

		Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
		List<Address> addresses;
		try {
			addresses = gcd.getFromLocation(loc.getLatitude(),
					loc.getLongitude(), 1);
			if (addresses.size() > 0)

				System.out.println(addresses.get(0).getSubLocality());

			Area = addresses.get(0).getSubLocality();
			
			/*--------printing current location---------*/
			s = Area;
			edittext.setText(s);

			/*-----getting user locations-----*/

			Area1 = alarmName.getText().toString(); // getting location from
			s1 = Area1;
			

			if (s.equalsIgnoreCase(s1)) 
			{


		   		mp.start();
		   		v1.vibrate(5000);
		   		
		    AlertDialog.Builder builder= new AlertDialog.Builder(innerlocation.this);
		    
		    builder.setTitle("Destination");
		    builder.setMessage("You Reached to  destination"); 
		    
		    builder.setPositiveButton("Stop Alarm",new DialogInterface.OnClickListener() {
		    	
		    	
		    			 public void onClick(DialogInterface dialog, int id) {  
		    			 // finish the current activity  
		    			 // AlertBoxAdvance.this.finish();
		    				 
		    				 if(mp.isPlaying())
		    				 {
		    		                mp.stop();
		    		                v1.cancel();
		    				 }
		    		            
		    			    dialog.cancel(); 
		    			 	 }
		    				
		    				});
		    
		    builder .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
		    							    		
		    			 public void onClick(DialogInterface dialog, int id) {  
		    			  // cancel the dialog box  
		    			  dialog.cancel();  
		    			  
		    			 }
		    			 
		    			 });  
		    			
		    AlertDialog alert = builder.create();  
		    alert.show();  
		    
		    			}  

		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public void onProviderDisabled(String provider) {
		Log.d("Latitude", "disable");
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.d("Latitude", "enable");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d("Latitude", "status");
	}
	
}
