package com.sio.util;

import com.sio.graphics.DefaultPixelTemplateImageGenerable;
import com.sio.graphics.DefaultSegmentTemplateImageGenerable;
import com.sio.graphics.ImageGenerableHandler;

/**
 * ����������Ԫ������ģʽ
 * 
 * @author S
 *
 */
public class DefaultImageGenerableFactory implements ImageGenerableFactory{

	public static ImageGenerableHandler imageGenerable;
	
	public static final DefaultImageGenerableFactory instance = new DefaultImageGenerableFactory();
	
	private DefaultImageGenerableFactory() {
//		respond chain
		ImageGenerableHandler pixelImageGenerableHandler = new DefaultPixelTemplateImageGenerable();
		ImageGenerableHandler segmentImageGenerableHandler = new DefaultSegmentTemplateImageGenerable();
		pixelImageGenerableHandler.setImageGenerableHandler(segmentImageGenerableHandler);
		imageGenerable = pixelImageGenerableHandler;
//		set combiner
		imageGenerable.setElementCombiner(DefaultElementCombinerFactory.instance.createCombiner());
	
	}

	@Override
	public ImageGenerableHandler createImageGenerable(){
		return imageGenerable;
	}
}
