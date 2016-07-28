package com.sio.graphics;

import java.awt.image.BufferedImage;

public class FlowTextElementCombiner extends ElementCombiner {

	@Override
	public void combineRequest(Element element, BufferedImage image) {
		if(element instanceof FlowTextElement){
			
		} else {
			if(getSuccessor() != null){
				getSuccessor().combineRequest(element, image);
			}
		}
	}

}
