package view.gui;

import java.util.List;

import model.shape.SelectBoxProxy;
import model.shape.Shape;

public class DrawAllShape {
	public DrawAllShape(List<Shape> shapeList,List<Shape> selectList) {
		PaintCanvasSingleton.clearCanvas();
				
		for(int i = 0; i <shapeList.size(); i++) {
			shapeList.get(i).execute();
		}
		
		for(int i = 0; i<selectList.size(); i++) {
			new SelectBoxProxy(selectList.get(i)).drawSelectBox();
		}
	}
}
