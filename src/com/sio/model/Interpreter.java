package com.sio.model;

/**
 * һ���������ӿڣ������ڹ۲��߶�������Ϊ�����ױ��̳��߽����Ķ���ӿڡ�
 * ���統��ȡ��һ���ֽ���󲻶����ֽں�����н���������ֱ�ӶԸýӿ�ʵ�ֵ����������OOP�ı�̲�����
 * @author S
 *
 */
public interface Interpreter {
	public static final int TYPE_TAG = 1;
	public static final int MAC_6 = 0;
	public static final int MAC_1 = 5;
	public static final int BATTARY = 6;
	public static final int PRODUCT_CODE_H = 8;
	public static final int PRODUCT_CODE_M = 9;
	public static final int PRODUCT_CODE_L = 10;
	public static final int INTVAL_TIME = 11;
	public static final int LCDTYPE = 12;
	public static final int SIGNAL = 13;
	/**
	 * 
	 * @return Prototype, could be any.
	 * 			Check the type (by call getType()) to ensure.<br>
	 * {
	 * <b>ENUM: TYPE_TAG = [1] is instance of InterpretedTag.</b>   
	 * }
	 */
	public Object getPrototype();
	public void setPrototype(Object obj);
	public void setType(int type);
	public int getType();
	/**
	 * This function should be call every time new data comes. And let the interpreter to solve the data convert to entity object.
	 * @param ip from socket IP.
	 * @param port from socket PORT.
	 * @param data raw data
	 */
	public void update(String ip, int port,byte[] data);
	
}
