package model.shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import model.Point;
import view.gui.PaintCanvasSingleton;

class Rectangle extends Shape{
	public Rectangle(ShapeConstructor shapeBuilt) {
		super(shapeBuilt);
	}

	private Color PrimaryColor;
	private Color SecondaryColor;
	private Graphics2D graphics;	
	
	@Override
	public void execute() {
		this.graphics = PaintCanvasSingleton.getGraphics();
		PrimaryColor = shapeBuilt.getPrimaryColor();
		SecondaryColor = shapeBuilt.getSecondaryColor();

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
		graphics.fillRect(shapeBuilt.getxMin(), shapeBuilt.getyMin(), shapeBuilt.getWidth(), shapeBuilt.getHeight());
	}
	
	private void OutLine() {
		graphics.setStroke(new BasicStroke(5));
		graphics.setColor(PrimaryColor);
		graphics.drawRect(shapeBuilt.getxMin(), shapeBuilt.getyMin(), shapeBuilt.getWidth(), shapeBuilt.getHeight());
	}
	
	private void OutLineAndFilled() {
		graphics.setColor(PrimaryColor);
		graphics.fillRect(shapeBuilt.getxMin(), shapeBuilt.getyMin(), shapeBuilt.getWidth(), shapeBuilt.getHeight());
		graphics.setStroke(new BasicStroke(5));
		graphics.setColor(SecondaryColor);
		graphics.drawRect(shapeBuilt.getxMin(), shapeBuilt.getyMin(), shapeBuilt.getWidth(), shapeBuilt.getHeight());
	}
	
	@Override
	public void drawSelectBox() {
		Graphics2D graphics = PaintCanvasSingleton.getGraphics();
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
		graphics.setStroke(stroke);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(shapeBuilt.getxMin()-5, shapeBuilt.getyMin()-5, shapeBuilt.getWidth()+10, shapeBuilt.getHeight()+10);
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
