package com.sio.model;

import java.util.Observer;
import java.util.Set;

public interface DeviceUtility extends Observer {
	
	public Set<AbstractAccessPoint> getAccessPoints();
	
}
