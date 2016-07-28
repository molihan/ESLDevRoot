package com.sio.graphics;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode2Image extends GenerableImage {
	
	public QRCode2Image() {
	}

	@Override
	public BufferedImage getGeneratedImage(String src, int width, int height) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		BufferedImage image = null;
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
//		去边框
		hints.put(EncodeHintType.MARGIN, 0);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(src,
					BarcodeFormat.QR_CODE, width, height, hints);
			image = bitMatrixToImage(bitMatrix);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return image;
	}


}
