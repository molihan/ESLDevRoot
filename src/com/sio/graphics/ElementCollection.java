package com.sio.graphics;

import java.util.List;

public interface ElementCollection {

	public List<Element> getElements();
	public void addElement(Element element);
	public void removeElement(Element element);
	public void clearElements();
	public Element getElement(int index);
	public int getElementSize();
	public boolean hasElement();
}
