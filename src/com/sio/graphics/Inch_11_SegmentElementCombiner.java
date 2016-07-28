package com.sio.graphics;

import java.awt.image.BufferedImage;

public class Inch_11_SegmentElementCombiner extends ElementCombiner{

	@Override
	public void combineRequest(Element element, BufferedImage image) {
		if(element instanceof Inch_11_SegmentElement){
			
		} else {
			if(getSuccessor() != null){
				getSuccessor().combineRequest(element, image);
			}
		}
	}

}
