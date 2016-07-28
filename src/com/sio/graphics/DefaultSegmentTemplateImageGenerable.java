package com.sio.graphics;

import java.awt.image.BufferedImage;

public class DefaultSegmentTemplateImageGenerable extends ImageGenerableHandler{

	@Override
	public BufferedImage getImageRequest(Template template) {
		if(template instanceof SegmentTemplate){
			
		} else {
			if(getSuccessor() != null){
				getSuccessor().getImageRequest(template);
			}
		}
		
		return null;
	}
	
}
