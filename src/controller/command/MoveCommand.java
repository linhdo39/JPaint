package controller.command;

import java.util.ArrayList;
import java.util.List;

import controller.Interface.ICommand;
import controller.Interface.IUndoRedo;
import model.shape.Shape;
import model.shape.ShapeConstructor;
import view.gui.DrawAllShape;

public class MoveCommand implements ICommand,IUndoRedo{
	private ShapeConstructor shapeBuilt;
	private List<Shape> shapeList, selectList, tempList = new ArrayList<>();
	private int deltaX, deltaY;

	public MoveCommand(ShapeConstructor shapeBuilt, List<Shape> shapeList, List<Shape> selectList) {
		this.shapeBuilt = shapeBuilt;
		this.shapeList = shapeList;
		this.selectList = selectList;
	}

	@Override
	public void run() {
		deltaX = shapeBuilt.EndPoint().getX() - shapeBuilt.StartPoint().getX();
		deltaY = shapeBuilt.EndPoint().getY() - shapeBuilt.StartPoint().getY();

		for(int i = 0; i < selectList.size(); i++) {
			selectList.get(i).move(deltaX, deltaY);
			tempList.add(selectList.get(i));
		}
		new DrawAllShape(shapeList, selectList);
		CommandHistory.add(this);
	}
	
	@Override
	public void undo() {
		for(Shape shape: tempList)
			shape.move(-deltaX, -deltaY);
		new DrawAllShape(shapeList, selectList);
	}

	@Override
	public void redo() {
		for(Shape shape: tempList)
			shape.move(deltaX, deltaY);
		new DrawAllShape(shapeList, selectList);
	}
}