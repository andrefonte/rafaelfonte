package com.andrerafaelfonte.uielement;

import com.andrerafaelfonte.R;
import com.andrerafaelfonte.support.event.LapEvent;
import com.andrerafaelfonte.support.event.LapEventListener;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class LapsAdapterView extends LinearLayout implements LapEventListener{
	
	public View mainView;
	private ChronometerItem chronometerItem;
	private ChronometerWithMilliseconds chrono;
	private Context context;
	private int numberOfLaps;
	private LinearLayout laps_linear_layout;
	private LinearLayout laps_linear_layout1;
	private boolean started = false;
	
	public LapsAdapterView(Context context) {
		super(context);
		
		this.context = context;
		numberOfLaps = 0;
		
		this.setOrientation(VERTICAL);
				
		setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.FILL_PARENT));
		
		
		chronometerItem = new ChronometerItem(context);
		chrono = chronometerItem.getWidget();
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 0, 20, 0);
		chrono.setLayoutParams(layoutParams);
		
		//chrono.setBackgroundColor(mapIndexToColor(i));
		addView(chrono);
		
		chronometerItem.addLapEventListener(this);
		
		View v = inflate(context, R.layout.laps, null);
		
		mainView = v;

		addView(v);
		
		laps_linear_layout =  (LinearLayout)findViewById(R.id.laps_linear_layout);

		
		laps_linear_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Change the view to lap/split/pace
				//laps_linear_layout.setVisibility(VISIBLE);
				
				ScrollView scrollview1 = (ScrollView)findViewById(R.id.ScrollView1);
				scrollview1.setVisibility(GONE);
				
				ScrollView scrollview12 = (ScrollView)findViewById(R.id.ScrollView12);
				scrollview12.setVisibility(VISIBLE);
				scrollview12.scrollTo(0, 0);
				
			}
		});
		
		laps_linear_layout1 =  (LinearLayout)findViewById(R.id.laps_linear_layout1);

		
		laps_linear_layout1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Change the view to lap/split/pace
				//laps_linear_layout.setVisibility(VISIBLE);
				
				ScrollView scrollview1 = (ScrollView)findViewById(R.id.ScrollView1);
				scrollview1.setVisibility(VISIBLE);
				scrollview1.scrollTo(0, 0);
				
				ScrollView scrollview12 = (ScrollView)findViewById(R.id.ScrollView12);
				scrollview12.setVisibility(GONE);
				
			}
		});
		
		Button startStopButton =  (Button)findViewById(R.id.laps_start_stop_button);
		
		startStopButton.setOnClickListener(mStartStopButton);
	}
	
	
	
	public void startStopChronometer() {
		if(started == false)
    	{
    		chronometerItem.startChronometer();
    		started = true;
    	}
    	else
    	{
    		chronometerItem.stopChronometer();
    		started = false;
    	}
	}
	
	Button.OnClickListener mStartStopButton = new OnClickListener() {	
		@Override
		public void onClick(View v) {
			startStopChronometer();
		}
	};
	
	@Override
	public void lapEventOccurred(LapEvent evt) {
		
		numberOfLaps++;
		
		String lapText = numberOfLaps + " - " + evt.getLap();
		
		TextView txtView = new TextView(context);
		txtView.setTextAppearance(context, android.R.style.TextAppearance_Medium);
		txtView.setText(lapText);
		
		txtView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		laps_linear_layout.addView(txtView, 0);
		
		
		TextView txtView1 = new TextView(context);
		txtView1.setTextAppearance(context, android.R.style.TextAppearance_Medium);
		txtView1.setText(lapText);
		
		txtView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		laps_linear_layout1.addView(txtView1);
	}
}
