package com.sio.graphics;

import java.awt.Color;

public abstract class AbstractElementDrawable implements Element{

	protected Template template;
	protected int x,y, width, height;
	protected Color color;
	
	public AbstractElementDrawable(Template template) {
		this.template = template;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract int getWidth();
	public abstract int getHeight();
	
	public final void setWidth(int width){
		this.width = width;
	}
	
	public final void setHeight(int height){
		this.height = height;
	}
	
	@Override
	public Template getTemplate() {
		return template;
	}
}
