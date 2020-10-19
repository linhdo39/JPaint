package controller.command;

import java.awt.Rectangle;
import java.util.List;

import controller.Interface.ICommand;
import model.shape.Shape;
import model.shape.ShapeConstructor;
import view.gui.DrawAllShape;

public class SelectCommand implements ICommand{
	private ShapeConstructor shapeBuilt;
	private ShapeConstructor shapeListBuilt;
	private List<Shape> shapeList,selectList;
	private boolean contain = false;
	private boolean collision = false;

	public SelectCommand(ShapeConstructor shapeBuilt, List<Shape> shapeList, List<Shape> selectList) {
		this.shapeBuilt = shapeBuilt;
		this.shapeList = shapeList;
		this.selectList = selectList;
	}
	
	@Override
	public void run() {
		for(int i = 0; i <shapeList.size(); i++) {
			selectList.clear();
			shapeListBuilt = shapeList.get(i).getShapeConstructor();
			Rectangle selectRect = new Rectangle(shapeBuilt.getxMin(), shapeBuilt.getyMin(), shapeBuilt.getWidth(), shapeBuilt.getHeight());
			Rectangle shapeRect = new Rectangle(shapeListBuilt.getxMin(), shapeListBuilt.getyMin(), shapeListBuilt.getWidth(), shapeListBuilt.getHeight());
			contain = shapeRect.contains(shapeBuilt.StartPoint().getX(),shapeBuilt.StartPoint().getY());
			collision = selectRect.intersects(shapeRect);
			//select all shapes contains the clicked point
			if(contain == true && collision == false) {
				for(int j =0; j<shapeList.size(); j++) {
					shapeListBuilt = shapeList.get(j).getShapeConstructor();
					shapeRect = new Rectangle(shapeListBuilt.getxMin(), shapeListBuilt.getyMin(), shapeListBuilt.getWidth(), shapeListBuilt.getHeight());
					if(shapeRect.contains(shapeBuilt.StartPoint().getX(),shapeBuilt.StartPoint().getY())) {
						selectList.add(shapeList.get(j));
					}
				}
				break;
			}
			
			//select all shapes when click and drag
			if (collision == true) {
				for(int j =0; j<shapeList.size(); j++) {
					shapeListBuilt = shapeList.get(j).getShapeConstructor();
					shapeRect = new Rectangle(shapeListBuilt.getxMin(), shapeListBuilt.getyMin(), shapeListBuilt.getWidth(), shapeListBuilt.getHeight());
					if(selectRect.intersects(shapeRect)) {
						selectList.add(shapeList.get(j));
					}
				}
				break;
			}
		}
		new DrawAllShape(shapeList, selectList);
	}
}
