package controller.command;

import java.util.List;

import controller.Interface.ICommand;
import model.shape.Shape;

public class CopyCommand implements ICommand{
	private List<Shape> selectList, copyList;
	public CopyCommand(List<Shape> selectList, List<Shape> copyList) {
		this.selectList = selectList;
		this.copyList = copyList;
	}

	@Override
	public void run() {
		copyList.clear();
		for(int i = 0; i <selectList.size(); i++) {
			copyList.add(selectList.get(i));
		}
	}


}
