package com.sio.model;


public interface APCommandable {
	
	public void clearCommand();
	
	public void sendCommand(byte[] command);
	
}
