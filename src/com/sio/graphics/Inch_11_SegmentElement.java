package com.sio.graphics;

public class Inch_11_SegmentElement extends AbstractSegmentElement{
	private static final int INCH_11_ELEMENT = 2;
	
	public Inch_11_SegmentElement(Template template) {
		super(template);
	}
	
	@Override
	public int getModelType() {
		return INCH_11_ELEMENT;
	}
}
