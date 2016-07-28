package com.sio.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class DefaultPixelTemplateImageGenerable extends ImageGenerableHandler {

	@Override
	public BufferedImage getImageRequest(Template template) {
		if(template instanceof PixelMatrixTemplate){
			BufferedImage image = new BufferedImage(((PixelMatrixTemplate) template).getWidth(), ((PixelMatrixTemplate) template).getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g2d = (Graphics2D) image.getGraphics();
			Color color = g2d.getColor();
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
			List<Element> elements = template.getElements();
			for(Element element : elements){
				elementCombiner.combineRequest(element, image);
			}
			g2d.setColor(color);
			return image;
		} else {
			if(getSuccessor() != null){
				getSuccessor().getImageRequest(template);
			}
		}
		
		return null;
	}

}
