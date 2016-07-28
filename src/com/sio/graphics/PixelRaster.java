package com.sio.graphics;

import java.awt.image.BufferedImage;

/**
 * METHOD: Respondent chain.
 * @author S.Sio
 *
 */
public interface PixelRaster {
	/**
	 * Convert an image into pixels
	 * @param img src image
	 * @param direction	ENUM from ImageCaster interface.<br> 
	 * This arguments indicates the direction of rasterize the image into pixels.
	 * @return	raw pixels.
	 */
	public byte[] getPixels(BufferedImage img, int direction);
}
