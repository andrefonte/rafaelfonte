package com.chronometer;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

public class ChronometerActivity extends Activity {
	Chronometer mChronometer;
	TextView milliseconds;
	Button newChrono;
	Context context;
	boolean started = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        newChrono = (Button) findViewById(R.id.button2);
        newChrono.setOnClickListener(mNewChronometer);
        context = this;
    }
    
    
    Button.OnClickListener mNewChronometer = new OnClickListener() {	
		@Override
		public void onClick(View v) {
			View linearLayout =  findViewById(R.id.info);
			
			ChronometerItem chronometerItem = new ChronometerItem(context);
			ChronometerWithMilliseconds chrono = chronometerItem.getWidget();
			chrono.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));

	        ((LinearLayout) linearLayout).addView(chrono);
			
		}
	};
}