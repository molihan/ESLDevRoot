package com.sio.util;

import com.sio.graphics.Template;

public interface TemplateFactory {
	public static final int PIXEL_TEMPLATE = 1;
	public static final int SEGMENT_TEMPLATE = 2;
	/**
	 * en:Create a template with proper settings<br>
	 * chn������һ���趨�õ�ģ�塣
	 * @param type enum: pixel = 1; segment = 2; extent.
	 * @return Template object.
	 */
	public Template createTemplate(int type);
	
}
