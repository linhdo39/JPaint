package model.shape;

import java.awt.Color;

import controller.Interface.IShapeDrawer;
import model.Point;
import model.ShapeShadingType;
import model.ShapeType;

public class ShapeConstructor{
	private Point pressedPoint, releasedPoint;
	private ShapeType Shape;
	private ShapeShadingType Shading;
	private Color PrimaryColor;
	private Color SecondaryColor;
	private IShapeDrawer shapeDraw;

	public ShapeConstructor (Point pressedPoint, Point releasedPoint, Color PrimaryColor, Color SecondaryColor, ShapeShadingType Shading, ShapeType Shape) {
		this.pressedPoint = pressedPoint;
		this.releasedPoint = releasedPoint;
		this.Shading = Shading;
		this.Shape = Shape;
		this.PrimaryColor = PrimaryColor;
		this.SecondaryColor = SecondaryColor;
	}
	
	public Shape drawShape(ShapeConstructor shapeBuilt) {
		switch(Shape) { 
		case RECTANGLE:
			shapeDraw = ShapeFactory.getRectangle(shapeBuilt); 
			break;
		case ELLIPSE:
			shapeDraw = ShapeFactory.getEllipses(shapeBuilt);  
			break;
		case TRIANGLE:
			shapeDraw = ShapeFactory.getTriangle(shapeBuilt); 
			break;
		}
		return (model.shape.Shape) shapeDraw;
	}
	
	public int getxMin() {  
		return Math.min(pressedPoint.getX(), releasedPoint.getX());
	}

	public int getyMin() {  
		return Math.min(pressedPoint.getY(), releasedPoint.getY());
	}

	public int getHeight() {  
		return Math.abs(releasedPoint.getY() - pressedPoint.getY());
	}

	public int getWidth() {  
		return Math.abs(releasedPoint.getX() - pressedPoint.getX());
	}

	public int getxMax() {  
		return Math.max(pressedPoint.getX(), releasedPoint.getX());
	}

	public int getyMax() { 
		return Math.max(pressedPoint.getY(), releasedPoint.getY());
	}

	public Point StartPoint() {  
		return pressedPoint;
	}

	public Point EndPoint() {  
		return releasedPoint;
	}

	public Color getPrimaryColor() {
		return PrimaryColor;
	}

	public Color getSecondaryColor() {
		return SecondaryColor;
	}

	public ShapeType getShape() {
		return Shape;
	}
	
	public ShapeShadingType getShading() {
		return Shading;
	}

}