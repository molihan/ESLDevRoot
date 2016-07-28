package com.sio.graphics;

import java.awt.image.BufferedImage;

public abstract class AbstractPixelRaster implements PixelRaster {
	private PixelRaster pixelRaster;

	@Override
	public abstract byte[] getPixels(BufferedImage img, int direction);

	public AbstractPixelRaster setNext(PixelRaster pixelRaster){
		this.pixelRaster = pixelRaster;
		return this;
	}
	
	public PixelRaster getNext(){
		return pixelRaster;
	}
}
