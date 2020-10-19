package model.shape;

import controller.Interface.ISelectProxy;

public class SelectBoxProxy implements ISelectProxy{
	private Shape shape;
	
	public SelectBoxProxy(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void drawSelectBox() {
		shape.drawSelectBox();
	}
	
}
