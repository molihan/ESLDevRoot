package com.sio.graphics;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Barcode2Image extends GenerableImage {

	public Barcode2Image() {
	}

	@Override
	public BufferedImage getGeneratedImage(String src, int width, int height) {
		BufferedImage image = null;
		int codeWidth = 3 + // start guard
				(7 * 6) + // left bars
				5 + // middle guard
				(7 * 6) + // right bars
				3; // end guard
		codeWidth = Math.max(codeWidth, width);

		BarcodeFormat format = BarcodeFormat.EAN_13;
		if (src.length() != 13 && src.length() <= 44) {
			format = BarcodeFormat.CODE_128;
		}
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
//		指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//		去边框
		hints.put(EncodeHintType.MARGIN, 0);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(src, format, codeWidth, height, hints);
			image = bitMatrixToImage(bitMatrix);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return image;
	}

}
