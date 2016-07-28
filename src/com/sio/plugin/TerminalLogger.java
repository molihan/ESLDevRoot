package com.sio.plugin;

public interface TerminalLogger {

	public void log(String log);
	public void log(Class<Terminal> clazz, String log);
}
