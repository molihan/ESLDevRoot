package com.sio.util;


import com.sio.graphics.CastSettingSelector;
import com.sio.graphics.DimensionSelector;
import com.sio.graphics.PixelRaster;

public abstract class ImageCasterDelegatesFactory {
	protected static CastSettingSelector castSettingSelector;
	protected static DimensionSelector dimensionSelector;
	protected static PixelRaster pixelRaster;
	
	public ImageCasterDelegatesFactory() {
	}

	public static CastSettingSelector getCastSettingSelector() {
		return castSettingSelector;
	}

	public static void setCastSettingSelector(
			CastSettingSelector castSettingSelector) {
		ImageCasterDelegatesFactory.castSettingSelector = castSettingSelector;
	}

	public static DimensionSelector getDimensionSelector() {
		return dimensionSelector;
	}

	public static void setDimensionSelector(DimensionSelector dimensionSelector) {
		ImageCasterDelegatesFactory.dimensionSelector = dimensionSelector;
	}

	public static PixelRaster getPixelRaster() {
		return pixelRaster;
	}

	public static void setPixelRaster(PixelRaster pixelRaster) {
		ImageCasterDelegatesFactory.pixelRaster = pixelRaster;
	}

	protected abstract void initialPixelRaster();
	
	protected abstract void initialCastSettingSelector();
	
	protected abstract void initialDimensionSelector();
}
