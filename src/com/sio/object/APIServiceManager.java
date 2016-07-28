package com.sio.object;

import com.sio.model.DeviceUtility;
import com.sio.model.net.UDPTransceiver;

public final class APIServiceManager {

	private static UDPTransceiver transceiver;
	private static DeviceUtility devices;
	
	public static UDPTransceiver getTransceiver() {
		return transceiver;
	}
	
	public static void setTransceiver(com.sio.model.net.UDPTransceiver transceiver) {
		APIServiceManager.transceiver = transceiver;
	}
	
	public static DeviceUtility getDevices() {
		return devices;
	}
	
	public static void setDevices(DeviceUtility devices) {
		APIServiceManager.devices = devices;
	}
	
	
}
