package com.fio.alarm;

import com.fio.alarm.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

	public class MainActivity extends Activity{
		
		
		
@Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	

	Button buttoninner=(Button)findViewById(R.id.button1);
	
	buttoninner.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent  innerintent=new Intent(getBaseContext(),Newalarm.class);
			startActivity(innerintent);
		
}
	});
	
	Button buttoninter=(Button)findViewById(R.id.button2);

	buttoninter.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent  interintent=new Intent(getBaseContext(),Newalarm.class);
			startActivity(interintent);
	
}
	});
	
}

}
	
