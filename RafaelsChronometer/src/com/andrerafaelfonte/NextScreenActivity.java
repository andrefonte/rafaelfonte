package com.andrerafaelfonte;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.andrerafaelfonte.uielement.LapsAdapterView;

public class NextScreenActivity extends Activity {

	private LinearLayout linearLayout;
	private List<LapsAdapterView> lapsAdapterViewList;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.chronometer);
	    
	    Bundle b = getIntent().getExtras();
	    
        int p = b.getInt("numberOfAthletes");
        
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout1);
        
        lapsAdapterViewList = new ArrayList<LapsAdapterView>();
        
        for(int i = 0; i < p; i++) {
        	LapsAdapterView lav = new LapsAdapterView(this);
        	lav.setBackgroundColor(mapIndexToColor(i));
        	lapsAdapterViewList.add(lav);
        	linearLayout.addView(lav);
        }        
	}
	
	// This method is called at button click because we assigned the name to the
	// "On Click property" of the button
 	public void myClickHandler(View view) {
 		switch (view.getId()) {
 		case R.id.button1:
 			
 			for (LapsAdapterView lav : lapsAdapterViewList) {
				lav.startStopChronometer();
			}
 			break;
 		}
 	}
	
	private int mapIndexToColor(int index)
	{
		int color = Color.BLACK;
		
		switch(index % 4)
		{
			case 0:
				color = Color.BLUE;
				break;
			case 1:
				color = Color.RED;
				break;
			case 2:
				color = Color.GREEN;
				break;
			case 3:
				color = Color.MAGENTA;
				break;
			case 4:
				color = Color.LTGRAY;
				break;
			default:
				color = Color.BLACK;
		}
		
		return color;
	}

}
