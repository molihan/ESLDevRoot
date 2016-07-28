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
	 * CHN:JavaBean �趨һ��ʵ����ImageGenerable�ӿڵ����࣬������ģ�弰Ԫ�ع���һ��������ͼƬ
	 * @param imageGenerable ImageGenerableHandler ����.
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
	 * CHN:��ȡ��ǰ��ģ��ͼƬԤ��
	 * @return BufferedImage һ��ͼ����󣬳�����һ��Ԥ��ͼ��.
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
