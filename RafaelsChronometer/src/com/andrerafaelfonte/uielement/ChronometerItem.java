package com.andrerafaelfonte.uielement;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;

import com.andrerafaelfonte.support.EventListenerList;
import com.andrerafaelfonte.support.event.LapEvent;
import com.andrerafaelfonte.support.event.LapEventListener;

public class ChronometerItem {
	//Chronometer myChronometer;
	ChronometerWithMilliseconds myChronometer;
	long elapsed = 0;
	boolean started = false;
	Context context;
	long test = 0;
	
	private EventListenerList listenerList;
	
	public ChronometerItem(Context context) {
		myChronometer = new ChronometerWithMilliseconds(context);
		myChronometer.setTextSize(myChronometer.getTextSize());
		this.context = context;
		myChronometer.setOnClickListener(mStartStopChronometer);
		
		listenerList = new EventListenerList();
	}
	
	// This methods allows classes to register for LapEvents
    public void addLapEventListener(LapEventListener listener) {
        listenerList.add(LapEventListener.class, listener);
    }

    // This methods allows classes to unregister for LapEvents
    public void removeLapEventListener(LapEventListener listener) {
        listenerList.remove(LapEventListener.class, listener);
    }
    
    // This private method is used to fire LapEvents
    private void fireLapEvent(LapEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==LapEventListener.class) {
                ((LapEventListener)listeners[i+1]).lapEventOccurred(evt);
            }
        }
    }
	
	public ChronometerWithMilliseconds getWidget(){
		return myChronometer;
	}
	
	Chronometer.OnClickListener mStartStopChronometer = new OnClickListener() {
        public void onClick(View v) {
        	
        	if(myChronometer.isRunning()) {
	        	LapEvent evt = new LapEvent(this, myChronometer.getText().toString());
	        	fireLapEvent(evt);
        	}
        	
        	//if(started == false)
        	//{
        	//	startChronometer();
        	//	started = true;
        	//	Toast.makeText(context,"Inicializou",300).show();
        	//}
        	//else
        	//{
        	//	stopChronometer();
        	//	started = false;
        	//	Toast.makeText(context,"Parou",300).show();
        	//}
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
