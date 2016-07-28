package com.sio.plugin;

import java.io.File;
import java.net.URI;
import java.util.Set;

import com.sio.graphics.Template;
import com.sio.model.AbstractAccessPoint;

public interface TerminalControl {

	public Set<AbstractAccessPoint> getDevices();
	
	public void sendImage(String mac, File file);
	
	public void sendTemplate(String mac, Template template);
	
	public void sendImage(String mac, URI uri);

}
