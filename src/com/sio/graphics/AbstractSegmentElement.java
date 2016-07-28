package com.sio.graphics;

public abstract class AbstractSegmentElement implements Element {
	
	protected Template template;
	protected int solidNum;
	
	public AbstractSegmentElement(Template template) {
		this.template = template;
	}

	@Override
	public Template getTemplate() {
		return template;
	}

	public int getSolidNum() {
		return solidNum;
	}

	public void setSolidNum(int solidNum) {
		this.solidNum = solidNum;
	}

	/**
	 * ���ص�ǰģ�������ͺ�/����.
	 * @return int ��ʾ�ͺŵ�Ψһֵ.
	 */
	public abstract int getModelType();
}
