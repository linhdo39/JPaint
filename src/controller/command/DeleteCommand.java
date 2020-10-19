package controller.command;

import java.util.ArrayList;
import java.util.List;

import controller.Interface.ICommand;
import controller.Interface.IUndoRedo;
import model.shape.Shape;
import view.gui.DrawAllShape;

public class DeleteCommand  implements ICommand,IUndoRedo{
	private List<Shape> shapeList, selectList;
	private List<Shape> tempSelectList = new ArrayList<>();
	public DeleteCommand(List<Shape> shapeList, List<Shape> selectList) {
		this.shapeList = shapeList;
		this.selectList = selectList;
	}

	@Override
	public void run() {
		tempSelectList.clear();
		for(int i = 0; i <shapeList.size(); i++) {
			for(int j = 0; j <selectList.size();j++) {
				if(shapeList.get(i) == selectList.get(j)) {
					shapeList.remove(selectList.get(j));
				}
				tempSelectList.add(selectList.get(j));
			}
		}
		selectList.clear();
		new DrawAllShape(shapeList, selectList);
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		for(Shape tempShape: tempSelectList) {
			shapeList.add(tempShape);
			selectList.add(tempShape);
		}
		new DrawAllShape(shapeList, selectList);
	}

	@Override
	public void redo() {
		for(Shape tempShape: tempSelectList) {
			shapeList.remove(tempShape);
			selectList.remove(tempShape);
		}
		new DrawAllShape(shapeList, selectList);
	}

}