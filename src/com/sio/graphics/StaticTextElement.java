package com.sio.graphics;

import java.awt.Font;
/**
 * This class represented to a static line of text. See more at: com.sio.graphics.Template.class
 * @author S
 *
 */
public class StaticTextElement extends AbstractElementDrawable {
	private String content;
	private Font font;
	private boolean underLine;
	private boolean inversed;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public boolean isUnderLine() {
		return underLine;
	}

	public void setUnderLine(boolean underLine) {
		this.underLine = underLine;
	}

	public boolean isInversed() {
		return inversed;
	}

	public void setInversed(boolean inversed) {
		this.inversed = inversed;
	}

	public StaticTextElement(Template template) {
		super(template);
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

 
}
