package com.sio.model;

import java.nio.ByteBuffer;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �̳���Observable�Ľ����������԰ѽ��յ�����ת����֪ͨ��ȥ��
 * @author S
 *
 */
public class DataReader extends Observable{
	private static final int THREAD_LIMIT = 1;
	private static final int A4_PACK_PROTOCAL_LENGTH = 14;
	private static final byte A4_PACK_PROTOCAL_HEAD = (byte) 0xA4;
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_LIMIT);

	private String src_ip;
	private int src_port;
	private byte[] data;
	private Interpreter interpreter;
	
	public DataReader() {

	}
	/**
	 * JavaBean ip getter
	 * DO NOT SUGGEST TO CALL.
	 * PLEASE GET FROM Interpreter class.
	 * @return ip address
	 */
	public String getSrc_ip() {
		return src_ip;
	}
	/**
	 * JavaBean ip setter
	 * @param src_ip ip address
	 */
	public void setSrc_ip(String src_ip) {
		this.src_ip = src_ip;
	}
	/**
	 * JavaBean port number getter. 
	 * DO NOT SUGGEST TO CALL.
	 * PLEASE GET FROM Interpreter class.
	 * @return port
	 */
	public int getSrc_port() {
		return src_port;
	}

	public void setSrc_port(int src_port) {
		this.src_port = src_port;
	}
	
	public byte[] getData() {
		return data;
	}
	/**
	 * Raw data
	 * @param data raw data
	 */
	public void setData(byte[] data) {
		String ip = src_ip;
		int port = src_port;
		this.data = data;
		
		if(data[0] == A4_PACK_PROTOCAL_HEAD){
			if((data.length-1) % A4_PACK_PROTOCAL_LENGTH == 0){
				for(int x=1; x<data.length; x+=A4_PACK_PROTOCAL_LENGTH){
					ByteBuffer buf = ByteBuffer.allocate(A4_PACK_PROTOCAL_LENGTH);
					buf.put(data,x,A4_PACK_PROTOCAL_LENGTH);
					GeneratingTag genTag = new GeneratingTag();
					genTag.setIp(ip);
					genTag.setPort(port);
					genTag.setData(buf.array());
					threadPool.execute(genTag);
				}
			}
		}
	}

	public Interpreter getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(Interpreter interpreter) {
		this.interpreter = interpreter;
	}
	
	private class GeneratingTag implements Runnable{
		private String ip;
		private int port;
		private byte[] data;
		
		@Override
		public void run() {
			interpreter.setType(Interpreter.TYPE_TAG);
			interpreter.update(getIp(), getPort(), getData());
			setChanged();
			notifyObservers(interpreter.getPrototype());
		}

		private String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		private int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
		
		private byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}
	}
}
