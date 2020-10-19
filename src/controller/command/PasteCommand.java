package controller.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.Interface.ICommand;
import controller.Interface.IUndoRedo;
import model.shape.Shape;
import view.gui.DrawAllShape;

public class PasteCommand implements ICommand,IUndoRedo{
	private List<Shape> shapeList, selectList, copyList;
	private List<Shape> tempList = new ArrayList<>();;
	private Shape newShape;
	private int offsetX = 200;
	
	public PasteCommand(List<Shape> shapeList, List<Shape> selectList, List<Shape> copyList) {
		this.shapeList = shapeList;
		this.selectList = selectList;
		this.copyList = copyList;
	}

	@Override
	public void run() {
		tempList.clear();
		for(int i = 0; i <copyList.size(); i++) {
			newShape = copyList.get(i).paste(copyList.get(i).getShapeConstructor(),offsetX);
			tempList.add(newShape);
			shapeList.add(newShape);
		}			
		new DrawAllShape(shapeList, selectList);
		CommandHistory.add(this);
	}
	
	@Override
	public void undo() {
		for(Shape tempShape: tempList) {
			shapeList.remove(tempShape);
			for(Iterator<Shape> selectIterator = selectList.iterator(); selectIterator.hasNext(); ) {
			    Shape selectShape = selectIterator.next();
			    if(selectShape.equals(tempShape)) {
			        selectIterator.remove();
			    }
			}
		}
		new DrawAllShape(shapeList, selectList);
	}

	@Override
	public void redo() {
		for(Shape tempShape: tempList)
			shapeList.add(tempShape);
		new DrawAllShape(shapeList, selectList);
	}

}


