package com.andrerafaelfonte;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class RafaelsChronometerActivity extends Activity {
    /** Called when the activity is first created. */
	
	private EditText editText;
	private Button goButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        editText = (EditText) findViewById(R.id.editText1);
        editText.setOnKeyListener(mOnKeyListener);
        
        goButton = (Button) findViewById(R.id.button1);
        
        editText.setOnFocusChangeListener(mOnFocusChangeListener);
        
    }
    
    
    EditText.OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			//TODO
		}
	};

    
    
    EditText.OnKeyListener mOnKeyListener = new OnKeyListener() {
		
    	@Override
    	public boolean onKey(View v, int keyCode, KeyEvent event) {
    		if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
    		{
    			// code to hide the soft keyboard
    			InputMethodManager imm = (InputMethodManager) getSystemService(
    	                Context.INPUT_METHOD_SERVICE);
    			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    		}
    		
    		if(editText.getText().toString().length() > 0)
			{
				goButton.setEnabled(true);
			}
			else
			{
				goButton.setEnabled(false);
			}
    		
    		return false;
    	}
    };
    
    // This method is called at button click because we assigned the name to the
 	// "On Click property" of the button
 	public void myClickHandler(View view) {
 		switch (view.getId()) {
 		case R.id.button1:
 			//Toast.makeText(this, "Please enter a valid number",
			//			Toast.LENGTH_LONG).show();
 			Intent myIntent = new Intent(view.getContext(), NextScreenActivity.class);
 			
 			String text = editText.getText().toString();
 			int intValue = Integer.parseInt(text);
 			
 			myIntent.putExtra("numberOfAthletes", intValue);
            startActivityForResult(myIntent, 0);
 			
 			
 			break;
 		}
 	}
}