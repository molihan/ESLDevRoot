/**
 * 
 */
package com.sio.model;


/**
 * @author S.Sio
 *
 */
public abstract class WirelessTag {
	public static final String READY = "ready";
	public static final String SENDING = "sending";
	public static final String RECEIVED = "received";
	public static final String COMPLETE = "complete";
	public static final String RESEND = "resend";
	public static final String FAIL = "fail";
	/**
	 *	Variable that contains Tag's MAC address.
	 */
	protected String mac;
	protected byte[] broadcastInfo;
	protected Tag tag;
	protected boolean online;
	protected String communication;
	protected long update_serial;
	protected long last_feedback;

	/**
	 * This is an abstract function that must rewrite by other sub-classes.
	 * And this function decides to use which method to send.
	 * @param data Send particular bytes to the tag.
	 */
	public abstract void write(byte[] data);
	
	public final String getMac() {
		return mac;
	}
	
	public final void setMac(String mac) {
		this.mac = mac;
	}
	
	public final byte[] getBroadcastInfo() {
		return broadcastInfo;
	}
	
	public final void setBroadcastInfo(byte[] broadcastInfo) {
		this.broadcastInfo = broadcastInfo;
	}
	/**
	 * Call this method for get Tag's properties. This object contains mostly useful data.
	 * @return Tag object
	 */
	public final Tag getTag() {
		return tag;
	}

	public final void setTag(Tag tag) {
		if(this.tag != null){
			if(this.tag.code_1() != tag.code_1()){
				communication = COMPLETE;
			}
		} else {
			communication = READY;
		}
		online = true;
		this.tag = tag;
		
	}
	/**
	 * READY ; SENDING ; RECEIVED ; COMPLETE ; RESEND ; FAIL ;
	 * @return communication state.
	 */
	public final String getCommuncation(){
		return communication;
	}
	
	public final long getLast_feedback() {
		return last_feedback;
	}

	public final void setLast_feedback(long last_feedback) {
		this.last_feedback = last_feedback;
	}
	
	public final boolean online(){
		return online;
	}
	
	public final void online(boolean online){
		this.online = online;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof WirelessTag){
			if(((WirelessTag) obj).getMac().equalsIgnoreCase(getMac())){
				return true;
			}
		} 
		return false;
	}
}
