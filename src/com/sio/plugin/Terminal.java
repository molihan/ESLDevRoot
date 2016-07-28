package com.sio.plugin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import com.sio.graphics.DefaultImageCaster;
import com.sio.graphics.ImageCaster;
import com.sio.graphics.Template;
import com.sio.model.AbstractAccessPoint;
import com.sio.model.DefaultUDPA1Pack;
import com.sio.model.Packer;
import com.sio.model.WirelessTag;
import com.sio.object.APIServiceManager;
/**
 * 
 * @author S
 *
 */
public abstract class Terminal implements TerminalUnit, TerminalRunnable, TerminalIO, TerminalLogger, TerminalControl{
	private String defaultOuputPath;
	
	public Terminal() {
		File temp = new File(".");
		temp = temp.getParentFile();
		temp = new File(temp, Terminal.class.getSimpleName());
		defaultOuputPath = temp.getAbsolutePath();
	}
	
	/**
	 * This function return a path. For some reasons the 'plugin' may needs a folder to organized.
	 * @return String contents the system default output folder's path.
	 */
	protected final String getOutputFolderPathString() {
		return defaultOuputPath;
	}
	/**
	 * This function return a file object. For some reasons the 'plugin' may needs a folder to organized.
	 * @return File target to the system default output folder's path.
	 */
	protected final File getOutputFolderPathFile(){
		return new File(defaultOuputPath);
	}
	/**
	 * Indicates file is already created or successfully created. If false, means not exist and could not create the folder.
	 * @return boolean if true,folder exists or created, otherwise false.
	 */
	protected final boolean initialOutputFolder(){
		boolean success = false;
		File dir = getOutputFolderPathFile();
		if(dir == null){
			success = false;
		} else {
			if(dir.exists()){
				success = true;
			} else {
				try{
					dir.mkdirs();
					success = true;
				} catch (SecurityException e){
					e.printStackTrace();
					success = false;
				}
			}
		}
		return success;
	}

	@Override
	public void afterStart() {
		
	}
	
	@Override
	public void beforeStart() {
		
	}
	
	@Override
	public void beforeStop() {
		
	}
	
	@Override
	public final Reader getConcreteReader() {
		return System.console().reader();
	}
	
	@Override
	public final Writer getConcreteWriter() {
		return System.console().writer();
	}
	
	@Override
	public Reader getReader() {
		return null;
	}
	
	@Override
	public Writer getWriter() {
		return null;
	}

	@Override
	public void log(String log) {
		
		try {
			Writer writer = getConcreteWriter();
			writer.write("#"+Thread.currentThread().getName() + "#, @" + System.currentTimeMillis() + " class[" + getClass().getSimpleName() + "]: " + log+"\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void log(Class<Terminal> clazz, String log) {
		try {
			Writer writer = getConcreteWriter();
			writer.write("#"+Thread.currentThread().getName() + "#, @" + System.currentTimeMillis() + " class[" + clazz.getSimpleName() + "]: " + log+"\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @return provide a collection which contains all online access points.
	 */
	@Override
	public final Set<AbstractAccessPoint> getDevices() {
		return APIServiceManager.getDevices().getAccessPoints();
	}
	
	@Override
	public final void sendImage(String mac, File file) {
		if(file.exists()){
			BufferedImage image = null;
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for(AbstractAccessPoint device : getDevices()){
				if(device.contains(mac)){
					WirelessTag tag = device.getTag(mac);
					ImageCaster caster = new DefaultImageCaster();
					byte[] data = caster.cast(image, tag.getTag().model());
					Packer packer = new DefaultUDPA1Pack();
					packer.setHead(mac, new Random().nextLong(), new java.util.Date(System.currentTimeMillis()));
					packer.setData(DefaultUDPA1Pack.ORDER_SEND_BW, data);
					byte[] pack = packer.getPack();
					tag.write(pack);
					break;
				}
			}
		} else {
			System.out.println("send image has been called. file not exist[i]");
		}
		
	}
	/**
	 * send an image throw URI address. This image will be download from network.
	 * @param mac MAC address from tag
	 * @param uri HTTP address.
	 */
	@Override
	public final void sendImage(String mac, URI uri) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Directly send a template to a tag.
	 * @param mac MAC address from tag
	 * @param template Template object.
	 */
	@Override
	public final void sendTemplate(String mac, Template template) {
		for(AbstractAccessPoint device : getDevices()){
			if(device.contains(mac)){
				WirelessTag tag = device.getTag(mac);		//get abstract applicant level tag;
				byte[] data = template.getByteArray();		//get pixels data
				Packer packer = new DefaultUDPA1Pack();		//packing data
				packer.setHead(mac, new Random().nextLong(), null);	//set packing head
				packer.setData(DefaultUDPA1Pack.ORDER_SEND_BW, data);		//set packing data and use
				byte[] pack = packer.getPack();				//get packed data
				tag.write(pack);							//write out by abstracted tag.
				break;
			}
		}
		
		
	}
	
	public boolean equals(Object obj){
		if(obj != null && obj instanceof Terminal){
			if (obj.getClass().getSimpleName().equalsIgnoreCase(this.getClass().getSimpleName())) {
				return true;
			}
		}
		return false;
	}
	
}
