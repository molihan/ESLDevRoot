package com.sio.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StaticTextElementCombiner extends ElementCombiner {

	@Override
	public void combineRequest(Element element, BufferedImage image) {
		if(element instanceof StaticTextElement){
			Graphics2D g2d = (Graphics2D) image.getGraphics();
			Color c = g2d.getColor();
			g2d.setColor(Color.BLACK);
			Font f = ((StaticTextElement) element).getFont();
			g2d.setFont(f);
			g2d.drawString(((StaticTextElement) element).getContent(), ((StaticTextElement) element).getX(), ((StaticTextElement) element).getY());
			g2d.setColor(c);
		} else {
			if(getSuccessor() != null){
				getSuccessor().combineRequest(element, image);
			}
		}
	}

}
