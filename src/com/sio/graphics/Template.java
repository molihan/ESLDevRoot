package com.sio.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Template implements ElementCollection, TemplateByteArray{

	protected ImageGenerableHandler imageGenerable;
	protected List<Element> elements = new ArrayList<>();
	protected ImageCaster imageCaster;
	protected int type;
	
	public ImageGenerableHandler getImageGenerable() {
		return imageGenerable;
	}

	/**
	 * CHN:JavaBean 设定一个实现了ImageGenerable接口的子类，他负责将模板及元素构成一幅完整的图片
	 * @param imageGenerable ImageGenerableHandler 对象.
	 */
	public void setImageGenerable(ImageGenerableHandler imageGenerable) {
		this.imageGenerable = imageGenerable;
	}
	
	public ImageCaster getImageCaster() {
		return imageCaster;
	}

	public void setImageCaster(ImageCaster imageCaster) {
		this.imageCaster = imageCaster;
	}
	/**
	 * CHN:获取当前的模板图片预览
	 * @return BufferedImage 一个图像对象，承载了一个预览图像.
	 */
	public final BufferedImage getImage(){
		return imageGenerable.getImageRequest(this);
	}

	@Override
	public void addElement(Element element) {
		elements.add(element);
	}
	
	@Override
	public void clearElements() {
		elements.clear();
	}
	
	@Override
	public Element getElement(int index) {
		return elements.get(index);
	}
	
	@Override
	public List<Element> getElements() {
		return elements;
	}
	
	@Override
	public void removeElement(Element element) {
		elements.remove(element);
	}
	
	@Override
	public int getElementSize() {
		return elements.size();
	}
	
	@Override
	public boolean hasElement() {
		return !elements.isEmpty();
	}
	
	@Override
	public byte[] getByteArray() {
		return imageCaster.cast(getImage(), type);
	}
}
