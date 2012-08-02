package com.andrerafaelfonte.uielement;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.TextView;
import java.text.DecimalFormat;

public class ChronometerWithMilliseconds extends TextView {
    @SuppressWarnings("unused")
	private static final String TAG = "Chronometer";

    public interface OnChronometerTickListener {

        void onChronometerTick(ChronometerWithMilliseconds chronometer);
    }

    private long mBase;
    private boolean mVisible;
    private boolean mStarted;
    private boolean mRunning;
    private OnChronometerTickListener mOnChronometerTickListener;

    private static final int TICK_WHAT = 2;

    public ChronometerWithMilliseconds(Context context) {
        this (context, null, 0);
    }

    public ChronometerWithMilliseconds(Context context, AttributeSet attrs) {
        this (context, attrs, 0);
    }

    public ChronometerWithMilliseconds(Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs, defStyle);

        init();
    }

    private void init() {
        mBase = SystemClock.elapsedRealtime();
        updateText(mBase);
    }
    
    public boolean isRunning() {
    	return mRunning;
    }

    public void setBase(long base) {
        mBase = base;
        dispatchChronometerTick();
        updateText(SystemClock.elapsedRealtime());
    }

    public long getBase() {
        return mBase;
    }

    public void setOnChronometerTickListener(
            OnChronometerTickListener listener) {
        mOnChronometerTickListener = listener;
    }

    public OnChronometerTickListener getOnChronometerTickListener() {
        return mOnChronometerTickListener;
    }

    public void start() {
        mStarted = true;
        updateRunning();
    }

    public void stop() {
        mStarted = false;
        updateRunning();
    }


    public void setStarted(boolean started) {
        mStarted = started;
        updateRunning();
    }

    @Override
    protected void onDetachedFromWindow() {
        super .onDetachedFromWindow();
        mVisible = false;
        updateRunning();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super .onWindowVisibilityChanged(visibility);
        mVisible = visibility == VISIBLE;
        updateRunning();
    }

    private synchronized void updateText(long now) {
        String text = getStringFromTimeDifference(now, mBase);
        
        setText(text);
    }

    public String getStringFromTimeDifference(long now, long mBase2) {
    	long timeElapsed = now - mBase2;
        
        DecimalFormat df = new DecimalFormat("00");
        
        int hours = (int)(timeElapsed / (3600 * 1000));
        int remaining = (int)(timeElapsed % (3600 * 1000));
        
        int minutes = (int)(remaining / (60 * 1000));
        remaining = (int)(remaining % (60 * 1000));
        
        int seconds = (int)(remaining / 1000);
        remaining = (int)(remaining % (1000));
        
        int milliseconds = (int)(((int)timeElapsed % 1000));
        
        String text = "";
        
        if (hours > 0) {
        	text += df.format(hours) + ":";
        }
        
       	text += df.format(minutes) + ":";
       	text += df.format(seconds) + ":";
       	String milliSec = String.format("%03d", milliseconds);
       	text += milliSec;//Integer.toString(milliseconds);
       	
       	return text;
	}

	private void updateRunning() {
        boolean running = mVisible && mStarted;
        if (running != mRunning) {
            if (running) {
                updateText(SystemClock.elapsedRealtime());
                dispatchChronometerTick();
                mHandler.sendMessageDelayed(Message.obtain(mHandler,
                        TICK_WHAT), 100);
            } else {
                mHandler.removeMessages(TICK_WHAT);
            }
            mRunning = running;
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message m) {
            if (mRunning) {
                updateText(SystemClock.elapsedRealtime());
                dispatchChronometerTick();
                sendMessageDelayed(Message.obtain(this , TICK_WHAT),
                        100);
            }
        }
    };

    void dispatchChronometerTick() {
        if (mOnChronometerTickListener != null) {
            mOnChronometerTickListener.onChronometerTick(this);
        }
    }
}
