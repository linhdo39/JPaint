package model.shape;

import controller.Interface.IShapeDrawer;

public class ShapeFactory {
	public static IShapeDrawer getRectangle(ShapeConstructor shapeBuilt) {	
		return new Rectangle(shapeBuilt);
	}
	
	public static IShapeDrawer getEllipses(ShapeConstructor shapeBuilt) {	
		return new Ellipses(shapeBuilt);
	}
	
	public static IShapeDrawer getTriangle(ShapeConstructor shapeBuilt) {	
		return new Triangle(shapeBuilt);
	}
}
