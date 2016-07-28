package com.sio.model;

import java.util.Collection;

public interface TagCollection {
	
	public void addTag(WirelessTag tag);
	
	public void removeTag(WirelessTag tag);
	
	public WirelessTag getTag(String mac);
	
	public Collection<WirelessTag> getTags();
	
	public boolean contains(WirelessTag tag);
	
	public boolean contains(String mac);
	
	public boolean removeTag(String mac);
}
