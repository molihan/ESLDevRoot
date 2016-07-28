package com.sio.graphics;

import java.awt.image.BufferedImage;

public interface ImageCaster {
	/**
	 * For these enum, it indicates the directions your function sampling from buffered image.<br>
	 * <p>
	 * 	S-----------W<br>
	 * 	|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>
	 * 	|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>
	 * 	H-----------E
	 * </p>
	 */
	public static final int DIRECTION_SWHE = 0;
	public static final int DIRECTION_SHWE = 1;
	public static final int DIRECTION_WSEH = 2;
	public static final int DIRECTION_WESH = 3;
	public static final int DIRECTION_HSEW = 4;
	public static final int DIRECTION_HESW = 5;
	public static final int DIRECTION_EHWS = 6;
	public static final int DIRECTION_EWHS = 7;
	
	/**
	 * <p>
	 * en�� Bring a BufferedImage object to bytes. More detail please go and see sub-classes.
	 * 		Commonly this function will go thru getProperImage() and getByte().
	 * <br>
	 * chn����һ��BufferedImageת��Ϊbyte[]���� ����ʵ�ּ����ࡣͨ������������������ʹ�ã��������getProperImage()��getByte().
	 * </p>
	 * @param image	BufferedImage�������һ����ת��ͼ��
	 * @param modal_type	An enum can figure out the procedures. Get this value by calling tag.getType()
	 * @return	����ͼ��ת���������ֽ�����
	 */
	public byte[] cast(BufferedImage image,int modal_type);
	/**
	 * en�� Convert buffered image to byte array. Detail please see sub-classes.
	 * chn:������ͼƬȡģΪ���ص㣬����ʵ�ּ����ࡣ
	 * @param image	src
	 * @param start_point which point should start from.
	 * @param direction	ENUM of ImageCaster figure out the directions.<br>
	 * @param MSB if true, then first bit is first pixel.
	 * @param inversed if true, the last pixel will comes first.
	 * @param bpp bit per pixel.
	 * <p>
	 *	S-----------W<br>
	 * 	|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>
	 * 	|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|<br>
	 * 	H-----------E
	 * </p>
	 * @return byte[] pixels data.
	 */
	public byte[] getByte(BufferedImage image, int start_point, int direction, boolean MSB, boolean inversed, int bpp);
	/**
	 * en: return a suitable image
	 * @param image	src
	 * @param modal_type tag's modal type
	 * @return	BufferedImage object, shrunks the image and redraw.
	 */
	public BufferedImage getProperImage(BufferedImage image,int modal_type);
}
