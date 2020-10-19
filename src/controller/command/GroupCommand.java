package controller.command;

import java.util.ArrayList;
import java.util.List;

import controller.Interface.ICommand;
import controller.Interface.IUndoRedo;
import model.shape.Shape;
import model.shape.ShapeGroup;
import view.gui.DrawAllShape;

public class GroupCommand implements ICommand, IUndoRedo{
	private List<Shape> shapeList, selectList;
	private List<Shape> tempList = new ArrayList<>();
	private ShapeGroup builder;
	public GroupCommand(List<Shape> shapeList, List<Shape> selectList) {
		this.shapeList = shapeList;
		this.selectList = selectList;
	}
	@Override
	public void run() {
		builder = new ShapeGroup(null);
		for(int i = 0; i< selectList.size(); i++) {
			tempList.add(selectList.get(i));
			builder.addChild(selectList.get(i));
			shapeList.remove(selectList.get(i));
		}
		selectList.clear();
		shapeList.add(builder);
		selectList.add(builder);
		new DrawAllShape(shapeList, selectList);
		CommandHistory.add(this);
	}
	
	@Override
	public void undo() {
		shapeList.remove(builder);
		selectList.remove(builder);
		for(Shape shape:tempList) {
			shapeList.add(shape);
			selectList.add(shape);
		}
		new DrawAllShape(shapeList, selectList);
	}
	
	@Override
	public void redo() {
		shapeList.add(builder);
		selectList.add(builder);
		for(Shape shape:tempList) {
			shapeList.remove(shape);
			selectList.remove(shape);
		}
		new DrawAllShape(shapeList, selectList);
	}
}
