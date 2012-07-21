package com.andrerafaelfonte.support.event;

import java.util.EventListener;

public interface LapEventListener extends EventListener {

	public void lapEventOccurred(LapEvent evt);
}
