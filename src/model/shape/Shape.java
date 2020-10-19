package model.shape;

import controller.Interface.ISelectProxy;
import controller.Interface.IShapeDrawer;

public abstract class Shape implements IShapeDrawer,ISelectProxy {
	ShapeConstructor shapeBuilt;
	
	public Shape(ShapeConstructor shapeBuilt) {
		this.shapeBuilt = shapeBuilt;
	}
	
	public abstract ShapeConstructor getShapeConstructor();
	public abstract int size();
}