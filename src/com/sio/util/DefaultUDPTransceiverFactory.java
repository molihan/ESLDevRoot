package com.sio.util;

import com.sio.model.DataQueue;
import com.sio.model.DataReader;
import com.sio.model.DeviceUtility;
import com.sio.model.Interpreter;
import com.sio.model.UDPAtomicDataQueue;
import com.sio.model.UDPInterpreter;
import com.sio.model.net.UDPTransceiver;
import com.sio.net.DefaultUDPTransceiver;
import com.sio.object.APIServiceManager;
/**
 * Default UDP Transceiver's factory class.
 * call super.createUDPTransceiver()  to invoke this function.
 * @author S
 *
 */
public class DefaultUDPTransceiverFactory implements UDPConnectionFactory{

	@Override
	public UDPTransceiver createUDPTransceiver() {
		DefaultUDPTransceiver transceiver = new DefaultUDPTransceiver();	//create transceiver READ
		DataReader dataReader = new DataReader();							//create reader
		Interpreter interpreter = new UDPInterpreter();						//create interpreter
		DeviceUtility deviceUtility = APIServiceManager.getDevices();
		dataReader.addObserver(deviceUtility);
		dataReader.setInterpreter(interpreter);								//set interpreter to reader
		transceiver.setObservable(dataReader);								//set reader to transceiver
		DataQueue queue = new UDPAtomicDataQueue();							//create queue
		transceiver.setQueue(queue);										//set queue
//		transceiver.startUDPEvent();
		return transceiver;
	}

}
