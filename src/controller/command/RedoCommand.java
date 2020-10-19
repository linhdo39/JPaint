package controller.command;

import controller.Interface.ICommand;

public class RedoCommand implements ICommand{

	@Override
	public void run() {
		CommandHistory.redo();
	}

}
