package com.chronometer;

import android.os.SystemClock;
import android.widget.Chronometer;

public class ChronometerItem {
	Chronometer myChronometer;
	long elapsed = 0;
	boolean started = false;
	
	
	public void startChronometer(){
		myChronometer.setBase(SystemClock.elapsedRealtime()-elapsed);
		myChronometer.start();
	}
	public void stopChronometer(){
		elapsed = SystemClock.elapsedRealtime() - myChronometer.getBase();
		myChronometer.stop();
	}
}
