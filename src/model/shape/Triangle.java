package model.shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import model.Point;
import view.gui.PaintCanvasSingleton;

class Triangle extends Shape {
	public Triangle(ShapeConstructor shapeBuilt) {
		super(shapeBuilt);
	}

	private Color PrimaryColor;
	private Color SecondaryColor;
	private int xPoint[] = new int[3];
	private int yPoint[] = new int[3];
	private Graphics2D graphics;
	
	@Override
	public void execute() {
		graphics = PaintCanvasSingleton.getGraphics();
		PrimaryColor = shapeBuilt.getPrimaryColor();
		SecondaryColor = shapeBuilt.getSecondaryColor();

		xPoint[0] = shapeBuilt.getxMin();
		xPoint[1] = shapeBuilt.getxMax();
		xPoint[2] = shapeBuilt.getxMin();

		yPoint[0] = shapeBuilt.getyMin();
		yPoint[1] = shapeBuilt.getyMax();
		yPoint[2] = shapeBuilt.getyMax();
		
		switch(shapeBuilt.getShading()) {
		case FILLED_IN: 
			Filled();
			break;
		case OUTLINE: 
			OutLine();
			break;
		case OUTLINE_AND_FILLED_IN: 
			OutLineAndFilled();
			break;
		}			
	}

	private void Filled() {
		graphics.setColor(PrimaryColor);
		graphics.fillPolygon(xPoint,yPoint,3);
	}

	private void OutLine() {
		graphics.setStroke(new BasicStroke(5));
		graphics.setColor(PrimaryColor);
		graphics.drawPolygon(xPoint,yPoint,3);
	}

	private void OutLineAndFilled() {
		graphics.setColor(PrimaryColor);
		graphics.fillPolygon(xPoint,yPoint,3);
		graphics.setStroke(new BasicStroke(5));
		graphics.setColor(SecondaryColor);
		graphics.drawPolygon(xPoint,yPoint,3);
	}
	
	@Override
	public void drawSelectBox() {
		Graphics2D graphics = PaintCanvasSingleton.getGraphics();
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
		graphics.setStroke(stroke);
		graphics.setColor(Color.BLACK);
		int xPoint[] = {shapeBuilt.getxMin()-5, shapeBuilt.getxMax()+15, shapeBuilt.getxMin()-5};
		int yPoint[] = {shapeBuilt.getyMin()-15,shapeBuilt.getyMax()+10, shapeBuilt.getyMax()+10};
		graphics.drawPolygon(xPoint, yPoint ,3);
	}
	
	public ShapeConstructor getShapeConstructor() {
		return shapeBuilt;
	}
	
	@Override
	public int size() {
		return 0;
	}

	@Override
	public void move(int deltaX, int deltaY) {
		Point newBeginPoint = new Point(shapeBuilt.StartPoint().getX() + deltaX, shapeBuilt.StartPoint().getY() +deltaY);
		Point newEndPoint = new Point(shapeBuilt.EndPoint().getX() +deltaX, shapeBuilt.EndPoint().getY() +deltaY);
		shapeBuilt = new ShapeConstructor(newBeginPoint, newEndPoint,shapeBuilt.getPrimaryColor(), shapeBuilt.getSecondaryColor(), shapeBuilt.getShading(), shapeBuilt.getShape());
	}
	
	@Override
	public Shape paste(ShapeConstructor shapeBuilt, int offsetX) {
		Point newBeginPoint = new Point(shapeBuilt.StartPoint().getX() + offsetX, shapeBuilt.StartPoint().getY());
		Point newEndPoint = new Point(shapeBuilt.EndPoint().getX() + offsetX, shapeBuilt.EndPoint().getY());
		ShapeConstructor newShapeBuilt = new ShapeConstructor(newBeginPoint, newEndPoint,shapeBuilt.getPrimaryColor(), shapeBuilt.getSecondaryColor(), shapeBuilt.getShading(), shapeBuilt.getShape());

		return newShapeBuilt.drawShape(newShapeBuilt);
	}
}
