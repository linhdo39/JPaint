package controller.command;

import controller.Interface.ICommand;

public class UndoCommand implements ICommand{

	@Override
	public void run() {
		CommandHistory.undo();
	}	
}
