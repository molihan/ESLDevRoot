package com.sio.graphics;

public class ImageElement extends AbstractElementDrawable {

	private String uri;
	
	public ImageElement(Template template) {
		super(template);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
