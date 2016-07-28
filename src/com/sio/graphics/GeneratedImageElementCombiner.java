package com.sio.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GeneratedImageElementCombiner extends ElementCombiner{

	@Override
	public void combineRequest(Element element, BufferedImage image) {
		if(element instanceof GeneratedImageElement){
			BufferedImage bImage = ((GeneratedImageElement) element).getGenerableImage().getGeneratedImage(((GeneratedImageElement) element).getSrc(), ((GeneratedImageElement) element).getWidth(), ((GeneratedImageElement) element).getHeight());
			Graphics2D g2d = (Graphics2D) image.getGraphics();
			Color c = g2d.getColor();
			g2d.setColor(Color.BLACK);
			g2d.drawImage(bImage, ((GeneratedImageElement) element).getX(), ((GeneratedImageElement) element).getY(),null);
			g2d.setColor(c);
		} else {
			if(getSuccessor() != null){
				getSuccessor().combineRequest(element, image);
			}
		}
	}
}
