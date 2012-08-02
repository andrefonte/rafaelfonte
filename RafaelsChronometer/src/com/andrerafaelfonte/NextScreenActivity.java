package com.andrerafaelfonte;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.andrerafaelfonte.uielement.LapsAdapterView;

public class NextScreenActivity extends Activity {

	private LinearLayout linearLayout;
	private List<LapsAdapterView> lapsAdapterViewList;
	private Context context;
	private int chronometersToCreate;
	private int chronometersCreated;
	private EditText input;
	private Button startStopButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    setContentView(R.layout.chronometer);
	    context = this;
	    
	    Bundle b = getIntent().getExtras();
	    
	    chronometersCreated = 0;
	    
	    chronometersToCreate = b.getInt("numberOfAthletes");
        
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout1);
        
        lapsAdapterViewList = new ArrayList<LapsAdapterView>();
        
        startStopButton =  (Button)findViewById(R.id.button1);
        startStopButton.setOnLongClickListener(mResetButton);
        
        createChronometer();
	}
	
	private void createChronometer() {
		if(chronometersCreated < chronometersToCreate) {
			
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
 		    input = new EditText(this);
 		    input.setId(12345);        
 		    alert.setView(input);
 		    alert.setTitle("Enter the athlete #" + (chronometersCreated + 1) + " name:");
 		    alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
 		        //@Override
 		        public void onClick(DialogInterface dialog, int which) {
 		            //EditText input = (EditText)dialog.findViewById(12345);
 		        	Editable value = input.getText();
 		        	String out = value.toString();
 		        	
 		        	if(out.length() == 0)
 		        	{
 		        		out = "Athlete #" + (chronometersCreated + 1);
 		        	}
 		        	
 		        	LapsAdapterView lav = new LapsAdapterView(context, out);
 		        	lav.setBackgroundColor(mapIndexToColor(chronometersCreated));
 		        	lapsAdapterViewList.add(lav);
 		        	linearLayout.addView(lav);
 		        	chronometersCreated++;
 		        	createChronometer();
 		        	startStopButton.setVisibility(Button.VISIBLE);
 		        }
 		    }); 		    
 		    alert.show();
			
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//setContentView(R.layout.myLayout);
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
 	
 	
 	
 	
 	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
 	    @Override
 	    public void onClick(DialogInterface dialog, int which) {
 	        switch (which){
 	        case DialogInterface.BUTTON_POSITIVE:
 	        	for (LapsAdapterView lav : lapsAdapterViewList) {
 					lav.resetChronometer();
 				}
 	            break;

 	        case DialogInterface.BUTTON_NEGATIVE:
 	            //No button clicked
 	            break;
 	        }
 	    }
 	};
 	
 	Button.OnLongClickListener mResetButton = new OnLongClickListener() {	
		@Override
		public boolean onLongClick(View v) {
			
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
		 	builder.setMessage("Are you sure you want to reset all chronometers?").setPositiveButton("Yes", dialogClickListener)
		 	    .setNegativeButton("No", dialogClickListener).show();
			return true;
		}
	};
	
	private int mapIndexToColor(int index)
	{
		int color = Color.BLACK;
		
		switch(index % 2)
		{
			case 0:
				color = Color.LTGRAY;
				break;
			default:
				color = Color.BLACK;
		}
		
		return color;
	}

}
