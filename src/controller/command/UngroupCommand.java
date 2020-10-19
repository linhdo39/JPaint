package controller.command;

import java.util.List;

import controller.Interface.ICommand;
import controller.Interface.IUndoRedo;
import model.shape.Shape;
import model.shape.ShapeGroup;
import view.gui.DrawAllShape;

public class UngroupCommand implements ICommand, IUndoRedo{
	private List<Shape> shapeList, selectList, tempList;
	private Shape temp;
	public UngroupCommand(List<Shape> shapeList, List<Shape> selectList) {
		this.shapeList = shapeList;
		this.selectList = selectList;
	}

	@Override
	public void run() {
		for(int i = 0 ; i <selectList.size(); i++) {
			if (selectList.get(i).size() > 0) {
				temp = selectList.get(i);
				selectList.remove(temp);
				shapeList.remove(temp);
				tempList = ((ShapeGroup) temp).getShapeGroup();
				for(int a = 0; a <tempList.size(); a++) {
					selectList.add(tempList.get(a));
					shapeList.add(tempList.get(a));
				}
				break;
			}
			else{
			}
		}
		new DrawAllShape(shapeList, selectList);
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		for(Shape shape:tempList) {
			selectList.remove(shape);
			shapeList.remove(shape);
		}
		selectList.add(temp);
		shapeList.add(temp);
		new DrawAllShape(shapeList, selectList);
	}

	@Override
	public void redo() {
		for(Shape shape:tempList) {
			selectList.add(shape);
			shapeList.add(shape);
		}
		selectList.remove(temp);
		shapeList.remove(temp);
		new DrawAllShape(shapeList, selectList);
	}
}
