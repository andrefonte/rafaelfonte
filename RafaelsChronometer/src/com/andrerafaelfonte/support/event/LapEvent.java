package com.andrerafaelfonte.support.event;

import java.util.EventObject;

public class LapEvent extends EventObject {

	private String lap;
	private String split;
	
	public LapEvent(Object source, String lap, String split) {
        super(source);
        this.lap = lap;
        this.split = split;
    }
	
	public String getLap() {
		return lap;
	}

	public String getSplit() {
		return split;
	}
}
