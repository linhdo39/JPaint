package controller.command;

import java.util.Iterator;
import java.util.List;

import controller.Interface.ICommand;
import controller.Interface.IUndoRedo;
import model.shape.Shape;
import model.shape.ShapeConstructor;
import view.gui.DrawAllShape;

public class DrawCommand implements ICommand, IUndoRedo{
	private Shape shape;
	private ShapeConstructor shapeBuilt;
	private List<Shape> shapeList, selectList;
	public DrawCommand(ShapeConstructor shapeBuilt,  List<Shape> shapeList, List<Shape> selectList) {
		this.shapeBuilt = shapeBuilt;
		this.shapeList = shapeList;
		this.selectList = selectList;
	}
	
	@Override
	public void run() {
		shape = shapeBuilt.drawShape(shapeBuilt);
		shape.execute();
		shapeList.add(shape);
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		shapeList.remove(shape);
		for(Iterator<Shape> selectIterator = selectList.iterator(); selectIterator.hasNext(); ) {
		    Shape selectShape = selectIterator.next();
		    if(selectShape.equals(shape)) {
		        selectIterator.remove();
		    }
		}
		new DrawAllShape(shapeList, selectList);
	}

	@Override
	public void redo() {
		shapeList.add(shape);
		new DrawAllShape(shapeList, selectList);
	}

}
