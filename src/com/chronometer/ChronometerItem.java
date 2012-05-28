package com.chronometer;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import android.widget.Toast;

public class ChronometerItem {
	//Chronometer myChronometer;
	ChronometerWithMilliseconds myChronometer;
	long elapsed = 0;
	boolean started = false;
	Context context;
	long test = 0;
	
	public ChronometerItem(Context context) {
		myChronometer = new ChronometerWithMilliseconds(context);
		myChronometer.setTextSize(myChronometer.getTextSize()*2);
		this.context = context;
		myChronometer.setOnClickListener(mStartStopChronometer);
	}
	
	public ChronometerWithMilliseconds getWidget(){
		return myChronometer;
	}
	
	Chronometer.OnClickListener mStartStopChronometer = new OnClickListener() {
        public void onClick(View v) {
        	if(started == false)
        	{
        		startChronometer();
        		started = true;
        		Toast.makeText(context,"Inicializou",300).show();
        	}
        	else
        	{
        		stopChronometer();
        		started = false;
        		Toast.makeText(context,"Parou",300).show();
        	}
            
        }
    };
	
	
	public void startChronometer(){
		myChronometer.setBase(SystemClock.elapsedRealtime()-elapsed);
		myChronometer.start();
	}
	public void stopChronometer(){
		elapsed = SystemClock.elapsedRealtime() - myChronometer.getBase();
		myChronometer.stop();
	}
}
