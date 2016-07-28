package com.sio.plugin;

import java.io.Reader;
import java.io.Writer;

public interface TerminalIO {
	/**
	 * Re-write this function if a better propriety writer is available.
	 * @return Writer object client provides
	 */
	public Writer getWriter();
	/**
	 * Re-write this function if a better propriety reader is available.
	 * @return Reader object client provides
	 */
	public Reader getReader();
	/**
	 * Default writer.
	 * @return Writer object API provides
	 */
	public Writer getConcreteWriter();
	/**
	 * Default reader.
	 * @return Writer object API provides
	 */
	public Reader getConcreteReader();
}
