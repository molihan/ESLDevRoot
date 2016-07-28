package com.sio.net;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Enumeration;

import com.sio.model.Packer;
import com.sio.model.net.UDPTransceiver;

public abstract class AbstractUDPTransceiver implements UDPTransceiver{
	protected static final int SELECTION_READ = SelectionKey.OP_READ;
	protected static final int SELECTION_WRITE = SelectionKey.OP_WRITE;
	private static final String IOEXCEPTION_UNINITIALED_CHANNEL = "DatagramChannel not initialized";
	protected final String BROADCAST_ADDRESS = "255.255.255.255";
	protected DatagramChannel datagramChannel;
	protected Selector selector;
	protected boolean isRunning;
	
	public AbstractUDPTransceiver() {

	}
	
	/**
	 * CHN:����һ�����Ӻ�����������������д����ɳ�ʼ���������ֶ�����
	 * @return datagramChannel ����ʼ������ᱻreturn�Ķ��󸲸ǡ�
	 */
	protected abstract DatagramChannel initialChannelHook();
	/**
	 * CHN:����һ�����Ӻ�����������������д����ɳ�ʼ���������ֶ�����
	 * @return selector ����ʼ������ᱻreturn�Ķ��󸲸ǡ�
	 */
	protected abstract Selector initialSelectorHook();
	/**
	 * This function must call in onEventCallBack() method block
	 * <br> or after-ward.<br>
	 * <b>DO NOT CALL IN CONSTRUCTION AND INITIALHOOKS</b>
	 * CHN:ע���ע�¼���
	 */
	protected final void registration(int selectionKey){
		try {
			datagramChannel.register(selector, selectionKey);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
	}
	/**
	 * CHN:��д�ú����Ը������ҵ���߼����ţ�ͬʱ������������
	 */
	protected abstract void onEventCallBack(DatagramChannel datagramChannel, Selector selector);
	/**
	 * CHN:����һ���̣߳������Ե��� function void onEventCallBack(DatagramChannel, Selector)����������ͨ������stopUDPEvent()��ֹͣ�����̡߳�
	 * @param synchronize if true, will block, if false, not block.
	 * @return the thread of udp event.
	 */
	@Override
	public final Thread startUDPEvent(boolean synchronize){
		datagramChannel = initialChannelHook();
		selector = initialSelectorHook();
		
		Thread t = null;
		if(!isRunning){
			isRunning = true;
			t = new Thread(new EventRunnable());
			if(synchronize){
				try {
					t.start();
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				t.start();
			}
		}
		return t;
	}
	/**
	 * CHN��ֹͣ��ǰ�����̡߳�
	 */
	@Override
	public final void stopUDPEvent(){
		isRunning = false;
		finalize();
	}
	/**
	 * �ڲ��߳���
	 * @author S
	 *
	 */
	private class EventRunnable implements Runnable{
		private static final int DEFAULT_THREAD_PRIORITY = Thread.NORM_PRIORITY;
		@Override
		public void run() {
			Thread.currentThread().setPriority(DEFAULT_THREAD_PRIORITY);
			while(isRunning){
				onEventCallBack(datagramChannel, selector);
			}
		}
	}
	
	/**
	 * ͨ��UDPChannel �� �������ݿ鷢���� Ŀ���ַ���˿�
	 * @param ip Ŀ��IP��ַ
	 * @param port Ŀ��˿ں�
	 * @param data ��������
	 * @throws IOException ��DatagramChnnel==null��δ���ó�ʼ������initialChannelHook()���׳����쳣����ʼ��������ͨ������������ֶ����á�
	 */
	@Override
	public void write(String ip, int port, byte[] data) throws IOException {
		if(datagramChannel == null){
			throw new IOException(IOEXCEPTION_UNINITIALED_CHANNEL);
		}
		try {
			datagramChannel.send(ByteBuffer.wrap(data), new InetSocketAddress(ip, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ͨ��UDPChannel �� �������ݿ�㲥�� Ŀ��˿�
	 * @param port Ŀ��˿ں�
	 * @param data ��������
	 */
	@Override
	public void broadCast(int port, byte[] data) throws IOException{
		if(datagramChannel == null){
			throw new IOException(IOEXCEPTION_UNINITIALED_CHANNEL);
		}
		try {
			datagramChannel.send(ByteBuffer.wrap(data), new InetSocketAddress(BROADCAST_ADDRESS, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ��ȡͨ�Ŷ˿ں�
	protected int getFreePort(String ip) { 
		//ΪUDP����е�Socket��,ֻ�����ж�UDPռ�õĶ˿�
		// ��������ֵ֮��Ķ˿ں�
		int MINPORT = 2048;
		int MAXPORT = 65000;
		for (; MINPORT < MAXPORT; MINPORT+=8) {
			// �ڶ���Ϊ���Ա���IP,������������,�򹹽�һ��InetAddress����
			try {
				
				DatagramSocket s = new DatagramSocket(new InetSocketAddress(ip, MINPORT));
				s.close();
				s = null;
				return MINPORT;
			} catch (BindException e) {
				// ��������˵��������,�������������.
				continue;
			} catch (SocketException e1) {
				e1.printStackTrace();
			}
	
		}
	
		// ��������þͷ���-1
		return -1;
	}
	
	public DatagramChannel getFreeChannel(String ip){
		DatagramChannel ch = null;
		int MINPORT = 2048;
		int MAXPORT = 65000;
		
		try {
			ch = DatagramChannel.open();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		for (; MINPORT < MAXPORT; MINPORT+=8) {
			// �ڶ���Ϊ���Ա���IP,������������,�򹹽�һ��InetAddress����
			try {
				
				ch.bind(new InetSocketAddress(ip, MINPORT));
				return ch;
			} catch (BindException e) {
				// ��������˵��������,�������������.
				continue;
			} catch (SocketException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
		return ch.isConnected()?ch:null;
	}
	
	public InetAddress getInet4AddressByHardwareAddress(String hardware_address){
		Enumeration<NetworkInterface> interfaces = null;
		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()){
				NetworkInterface netInterface = interfaces.nextElement();
				if(netInterface.getHardwareAddress() != null)
				if(Packer.fromBytesTo16radix(netInterface.getHardwareAddress()).equalsIgnoreCase(hardware_address)){
					Enumeration<InetAddress> inets = netInterface.getInetAddresses();
					while(inets.hasMoreElements()) {
						InetAddress addr = inets.nextElement();
						if(addr instanceof Inet4Address){
							return addr;
						}
					}
					return null;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void finalize(){
		if(datagramChannel != null && datagramChannel.isOpen())
		try {
			datagramChannel.disconnect();
			datagramChannel.socket().close();
			datagramChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		datagramChannel = null;
		if(selector != null && selector.isOpen())
		try {
			selector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		selector = null;
	}
	
	@Override
	public String getLocalIP() {
		try {
			return ((InetSocketAddress)datagramChannel.getLocalAddress()).getAddress().getHostAddress();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getLocalPort() {
		try {
			return ((InetSocketAddress)datagramChannel.getLocalAddress()).getPort();
		} catch (IOException e) {
			e.printStackTrace();
		};
		return -1;
	}
}
