package com.sio.plugin;

/**
 * This interface represented a terminal holding service. All control command and serial data should coming through.
 * @author S
 *
 */
public interface TerminalUnit {
	/**
	 * Function that calling before 'start() function' been called.
	 */
	public void beforeStart();
	/**
	 * Function that calling after 'start() function' been called.
	 */
	public void afterStart();
	/**
	 * Function that calling after 'stop() function' been called.
	 */
	public void beforeStop();
	
}
