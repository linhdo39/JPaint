package controller.command;

import java.util.Stack;

import controller.Interface.IUndoRedo;

class CommandHistory {
	private static final Stack<IUndoRedo> undoStack = new Stack<IUndoRedo>();
	private static final Stack<IUndoRedo> redoStack = new Stack<IUndoRedo>();

	public static void add(IUndoRedo cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoRedo c = undoStack.pop();
			redoStack.push(c);
			c.undo();
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoRedo c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		return result;
	}
}
