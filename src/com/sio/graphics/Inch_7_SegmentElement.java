package com.sio.graphics;

public class Inch_7_SegmentElement extends AbstractSegmentElement{
	private static final int INCH_7_ELEMENT = 1;
	
	public Inch_7_SegmentElement(Template template) {
		super(template);
	}
	
	@Override
	public int getModelType() {
		return INCH_7_ELEMENT;
	}

}
