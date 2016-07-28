package com.sio.graphics;

public interface DimensionSelector {
	
	/**
	 * en: get certain width.
	 * @param modal_type	tag's type.
	 * @return	integer width.
	 */
	public int getWidth(int modal_type);
	/**
	 * en: get certain width.
	 * @param modal_type	tag's type.
	 * @return integer height.
	 */
	public int getHeight(int modal_type);
}
