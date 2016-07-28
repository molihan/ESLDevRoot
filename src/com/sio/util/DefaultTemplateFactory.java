package com.sio.util;

import com.sio.graphics.DefaultImageCaster;
import com.sio.graphics.PixelMatrixTemplate;
import com.sio.graphics.SegmentTemplate;
import com.sio.graphics.Template;

/**
 * 创建模板的工厂类
 * @author S
 *
 */
public class DefaultTemplateFactory implements TemplateFactory{

	public static final TemplateFactory instance = new DefaultTemplateFactory();
	
	private DefaultTemplateFactory() {
		
	}

	@Override
	public Template createTemplate(int type) {
		Template template = null;
		if(TemplateFactory.PIXEL_TEMPLATE == type){
			template = new PixelMatrixTemplate();
			template.setImageCaster(new DefaultImageCaster());
			template.setImageGenerable(DefaultImageGenerableFactory.instance.createImageGenerable());
		} else if (TemplateFactory.SEGMENT_TEMPLATE == type){
			template = new SegmentTemplate();
			template.setImageGenerable(DefaultImageGenerableFactory.instance.createImageGenerable());
		}
		return template;
	}
}
