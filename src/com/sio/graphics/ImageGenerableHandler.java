package com.sio.graphics;

import java.awt.image.BufferedImage;

/**
 * EN： model:respondond chain.
 * CHN: 责任链模式。
 * @author S
 *
 */
public abstract class ImageGenerableHandler {

	protected ImageGenerableHandler successor;

	protected static ElementCombiner elementCombiner;
	
	/**
	 * EN: Get an BufferedImage when you called.
	 * @param template a template object
	 * @return An image object.
	 */
	public abstract BufferedImage getImageRequest(Template template);
	
	/**
	 * Get method
	 * @return This function returns a next-procedure handler.
	 */
	public ImageGenerableHandler getSuccessor() {
		return successor;
	}
	
	public void setImageGenerableHandler(ImageGenerableHandler successor){
		this.successor = successor;
	}
	
	public ElementCombiner getElementCombiner() {
		return elementCombiner;
	}

	public void setElementCombiner(ElementCombiner elementCombiner) {
		ImageGenerableHandler.elementCombiner = elementCombiner;
	}
}
