package com.sio.model;

public interface StreamProvider {
	public static final int TCP_IP = 0;
	public static final int UDP = 1;
	/**
	 * 
	 * @return provide a default object instance of StreamProvider.
	 */
	public StreamProvider getDefaultStreamProvider();
	/**
	 * 
	 * @param streamProvider setup a default provider
	 */
	public void setDefaultStream(StreamProvider streamProvider);
	
	public void getDataQueue(int type, int port);
	
}
