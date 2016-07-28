package com.sio.graphics;

import java.awt.image.BufferedImage;

public abstract class ElementCombiner {
	protected ElementCombiner successor;
	
	public abstract void combineRequest(Element element, BufferedImage image);
	
	public ElementCombiner getSuccessor(){
		return successor;
	}
	
	public void setSuccessor(ElementCombiner successor){
		this.successor = successor;
	}
	
}
