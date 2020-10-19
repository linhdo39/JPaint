package model.shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import model.Point;
import view.gui.PaintCanvasSingleton;

public class ShapeGroup extends Shape{
	public ShapeGroup(ShapeConstructor shapeBuilt) {
		super(shapeBuilt);
	}
	
	private List<Shape> GroupItems = new ArrayList<>();
	private int xMax, xMin, yMax, yMin;
	private ShapeConstructor newGroupConstructor;
	
	@Override
	public void execute() {
		
		xMax = GroupItems.get(0).getShapeConstructor().getxMax();
		yMax = GroupItems.get(0).getShapeConstructor().getyMax();
		xMin = GroupItems.get(0).getShapeConstructor().getxMin();
		yMin = GroupItems.get(0).getShapeConstructor().getyMin();
		for(Shape Item: GroupItems) {
			xMax = Math.max(xMax,Item.getShapeConstructor().getxMax());
			yMax = Math.max(yMax,Item.getShapeConstructor().getyMax());
			xMin = Math.min(xMin,Item.getShapeConstructor().getxMin());
			yMin = Math.min(yMin,Item.getShapeConstructor().getyMin());
		}
	
		Point newBeginPoint =  new Point(xMin, yMin);
		Point newEndPoint = new Point(xMax, yMax);
		newGroupConstructor = new ShapeConstructor(newBeginPoint, newEndPoint,null, null, null, null);
		for(Shape Item:GroupItems)
			Item.execute();
	}

	public void addChild(Shape shape) {
		GroupItems.add(shape);
		}
	
	public List<Shape> getShapeGroup(){
		return GroupItems;
	}
	
	@Override
	public void drawSelectBox() {
		Graphics2D graphics = PaintCanvasSingleton.getGraphics();
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
		graphics.setStroke(stroke);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(newGroupConstructor.getxMin()-5, newGroupConstructor.getyMin()-5, newGroupConstructor.getWidth()+10, newGroupConstructor.getHeight()+10);
	}
	
	@Override
	public int size() {
		return GroupItems.size();
	}
	
	@Override
	public ShapeConstructor getShapeConstructor() {
		return newGroupConstructor;
	}

	@Override
	public void move(int deltaX, int deltaY) {
		if(GroupItems.size() > 0) {
			for(Shape Item: GroupItems) {
				Item.move(deltaX, deltaY);
				Item.execute();
			}		
		}	
	}

	@Override
	public Shape paste(ShapeConstructor shapeBuilt, int offsetX) {
		ShapeGroup shape = new ShapeGroup(null);
		if(GroupItems.size() > 0)
			for(Shape Item:GroupItems) {
				shape.addChild(Item.paste(Item.getShapeConstructor(), offsetX));
				shape.execute();
			}
		return shape;
	}
}
