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
	 * 返回当前模板所属型号/种类.
	 * @return int 表示型号的唯一值.
	 */
	public abstract int getModelType();
}
