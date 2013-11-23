package com.fio.alarm;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.fio.alarm.R;

public class interlocation extends Activity implements LocationListener{
	
	
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected Context context;
	EditText edittext;
	EditText edittext2;
	String lat;
	String lon;
	String provider;
	protected String latitude,longitude; 
	Vibrator v1;
	MediaPlayer mp;
	Button stopbutton;
	

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.interlocation);

     
 v1 = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
 mp=MediaPlayer.create(this,R.raw.alarm);

 stopbutton=(Button)findViewById(R.id.button2);

edittext = (EditText) findViewById(R.id.editText1);


LocationManager locationManager =(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
}
@Override
public void onLocationChanged(Location loc) {

 longitude = "Longitude: " +loc.getLongitude();    
 latitude = "Longitude: " +loc.getLatitude();    
	
	  /*----------to get City-Name from coordinates ------------- */  

	 	String cityname = null;              
	    String Area = null;
	  
	    
	    Geocoder gcd = new Geocoder(getBaseContext(),   
	 Locale.getDefault());               
	    List<Address>  addresses;    
	    try {    
	    addresses = gcd.getFromLocation(loc.getLatitude(), loc  
	 .getLongitude(), 1);    
	    if (addresses.size() > 0) 
	    	
	       System.out.println(addresses.get(0).getSubLocality());
	    
	       Area=addresses.get(0).getSubLocality();
	       cityname=addresses.get(0).getLocality();
	       
	       String s=Area;
		   edittext.setText(s); 
		   
		   cityname=edittext2.getText().toString();
		   String s1=cityname;
		   
		   if(s.equalsIgnoreCase(s1))
		   {
			   mp.start();
		   		v1.vibrate(5000);
		   		
		    AlertDialog.Builder builder= new AlertDialog.Builder(interlocation.this);
		    
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
	Log.d("Latitude","disable");
}

@Override
public void onProviderEnabled(String provider) {
Log.d("Latitude","enable");
}

@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
Log.d("Latitude","status");
}


}


