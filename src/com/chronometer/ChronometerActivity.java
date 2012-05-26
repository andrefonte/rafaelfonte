package com.chronometer;


import android.app.Activity;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;

public class ChronometerActivity extends Activity {
	Chronometer mChronometer;
	TextView milliseconds;
	Button resetButton;
	boolean started = false;
	long test = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mChronometer = (Chronometer) findViewById(R.id.chronometer1);
        mChronometer.setOnClickListener(mStartStopChronometer);
        mChronometer.stop();
        
        resetButton = (Button) findViewById(R.id.button1);
        resetButton.setOnClickListener(mResetChronometer);
        
           
    }
    
    Chronometer.OnClickListener mStartStopChronometer = new OnClickListener() {
        public void onClick(View v) {
        	if(started == false)
        	{
        		mChronometer.setBase(SystemClock.elapsedRealtime()-test);
        		mChronometer.start();
        		started = true;
        		Toast.makeText(getApplicationContext(),"Inicializou",300).show();
        	}
        	else
        	{
        		test = SystemClock.elapsedRealtime() - mChronometer.getBase();
        		mChronometer.stop();
        		started = false;
        		Toast.makeText(getApplicationContext(),"Parou",300).show();
        	}
            
        }
    };
    
    
    Button.OnClickListener mResetChronometer = new OnClickListener() {	
		@Override
		public void onClick(View v) {
			mChronometer.setBase(SystemClock.elapsedRealtime());
			test = 0;			
			
		}
	};
}