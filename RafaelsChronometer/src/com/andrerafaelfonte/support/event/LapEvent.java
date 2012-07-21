package com.andrerafaelfonte.support.event;

import java.util.EventObject;

public class LapEvent extends EventObject {

	private String lap;
	
	public LapEvent(Object source, String lap) {
        super(source);
        this.lap = lap;
    }
	
	public String getLap() {
		return lap;
	}
}
