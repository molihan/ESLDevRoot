package com.sio.graphics;

import java.awt.Font;

public class FlowTextElement extends AbstractElementDrawable {
	private String src;
	private String refer_basic, refer_src;
	private Font font;
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getRefer_basic() {
		return refer_basic;
	}

	public void setRefer_basic(String refer_basic) {
		this.refer_basic = refer_basic;
	}

	public String getRefer_src() {
		return refer_src;
	}

	public void setRefer_src(String refer_src) {
		this.refer_src = refer_src;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public boolean isUnderline() {
		return underline;
	}

	public void setUnderline(boolean underline) {
		this.underline = underline;
	}

	public boolean isInversed() {
		return inversed;
	}

	public void setInversed(boolean inversed) {
		this.inversed = inversed;
	}

	private boolean underline,inversed;
	
	public FlowTextElement(Template template) {
		super(template);
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
