package com.sio.util;

import com.sio.graphics.ElementCombiner;
import com.sio.graphics.FlowTextElementCombiner;
import com.sio.graphics.GeneratedImageElementCombiner;
import com.sio.graphics.ImageElementCombiner;
import com.sio.graphics.Inch_11_SegmentElementCombiner;
import com.sio.graphics.Inch_7_SegmentElementCombiner;
import com.sio.graphics.StaticTextElementCombiner;

/**
 * 工厂类可以返回元素组合器
 * 享元、责任链、工厂模式
 * @author S
 *
 */
public class DefaultElementCombinerFactory implements ElementCombinerFactory {

	private ElementCombiner elementCombiner;
	
	public static final ElementCombinerFactory instance = new DefaultElementCombinerFactory();
	
	private DefaultElementCombinerFactory() {
		//责任链初始化
		ElementCombiner staticElementCombiner = new StaticTextElementCombiner();
		ElementCombiner flowElementCombiner = new FlowTextElementCombiner();
		ElementCombiner generatedElementCombiner = new GeneratedImageElementCombiner();
		ElementCombiner imageElementCombiner = new ImageElementCombiner();
		ElementCombiner seg_7_ElementCombiner = new Inch_7_SegmentElementCombiner();
		ElementCombiner seg_8_ElementCombiner = new Inch_11_SegmentElementCombiner();
		staticElementCombiner.setSuccessor(flowElementCombiner);
		flowElementCombiner.setSuccessor(generatedElementCombiner);
		generatedElementCombiner.setSuccessor(imageElementCombiner);
		imageElementCombiner.setSuccessor(seg_7_ElementCombiner);
		seg_7_ElementCombiner.setSuccessor(seg_8_ElementCombiner);
		elementCombiner = staticElementCombiner;
	}

	@Override
	public ElementCombiner createCombiner() {
		return elementCombiner;
	}

}
