package com.sio.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageElementCombiner extends ElementCombiner{

	@Override
	public void combineRequest(Element element, BufferedImage image) {
		if(element instanceof ImageElement){
			Graphics2D g2d = (Graphics2D) image.getGraphics();
			Color c = g2d.getColor();
			g2d.setColor(Color.BLACK);
			BufferedImage dst = null;
			if(new File(((ImageElement) element).getUri()).exists()){
				try(FileInputStream in = new FileInputStream(((ImageElement) element).getUri())){
					dst = ImageIO.read(in);
					
				} catch (IOException e){
					e.printStackTrace();
				}
			}
			
			if(dst != null){
				g2d.drawImage(dst, ((ImageElement) element).getX(), ((ImageElement) element).getY(), ((ImageElement) element).getWidth(), ((ImageElement) element).getHeight(), null);
			} else {
				int x = ((ImageElement) element).x;
				int y = ((ImageElement) element).y;
				int w = ((ImageElement) element).width;
				int h = ((ImageElement) element).height;
				g2d.drawRect(x, y, w, h);
				g2d.drawLine(x, y, x+w, y+h);
				g2d.drawLine(x, y+h, x+w, y);
				g2d.drawString("IMAGE NOT FOUND", x, y+(h/2));
			}
			g2d.setColor(c);
		} else {
			if(getSuccessor() != null){
				getSuccessor().combineRequest(element, image);
			}
		}
	}
	
}
