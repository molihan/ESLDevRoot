package com.sio.graphics;

/**
 * @author S
 *
 */
public interface CastSettingSelector {
	/**
	 * en:Help to choose a direction.
	 * <br>
	 * chn:指定一个枚举值用来刷新。
	 * 
	 * @param modal_type tag's modal(type)
	 * @return	enum number.
	 */
	public int selectDirection(int modal_type);
	public int selectStartingPoint(int modal_type);
	public boolean selectMSB(int modal_type);
	public boolean selectInversed(int modal_type);
	public int selectBitPerPixel(int modal_type);
	
}
