package com.sio.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.google.zxing.common.BitMatrix;

public abstract class GenerableImage {
	public static final int BARCODE = 0;
	public static final int QRCODE = 1;
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int WHITE = Color.WHITE.getRGB();
	/**
	 * 
	 * @param src	content
	 * @param width	image prefer width
	 * @param height	image prefer height
	 * @return
	 */
	public abstract BufferedImage getGeneratedImage(String src, int width, int height);
	
	protected final BufferedImage bitMatrixToImage(BitMatrix bitMatrix) {
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
}
