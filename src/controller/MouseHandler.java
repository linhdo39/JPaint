package controller;

import java.awt.event.*;
import java.util.List;

import controller.Interface.ICommand;
import controller.command.DrawCommand;
import controller.command.MoveCommand;
import controller.command.SelectCommand;
import model.ColorSingleton;
import model.Point;
import model.StartAndEndPointMode;
import model.persistence.ApplicationState;
import model.shape.Shape;
import model.shape.ShapeConstructor;

public class MouseHandler extends MouseAdapter{
	private Point pressedPoint;
	private Point releasedPoint;
	private ApplicationState appState;
	private ShapeConstructor shapeBuilt;
	private List<Shape> shapeList, selectList;
	public MouseHandler(ApplicationState appState ,List<Shape> shapeList, List<Shape> selectList) {
		this.appState = appState;
		this.shapeList = shapeList;
		this.selectList = selectList;
	}

	@Override
	public void mousePressed(MouseEvent point) {
		pressedPoint = new Point(point.getX(), point.getY());
	}

	@Override
	public void mouseReleased(MouseEvent point) {
		releasedPoint = new Point(point.getX(), point.getY());
		command();
	}

	public void command() { 
		shapeBuilt = new ShapeConstructor(pressedPoint, releasedPoint, ColorSingleton.getColor(appState.getActivePrimaryColor()),ColorSingleton.getColor(appState.getActiveSecondaryColor()), appState.getActiveShapeShadingType(), appState.getActiveShapeType());
		ICommand command = null;
		if(appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW) {
			command = new DrawCommand(shapeBuilt, shapeList,selectList);
		}
		else if (appState.getActiveStartAndEndPointMode()== StartAndEndPointMode.SELECT) {
			command = new SelectCommand(shapeBuilt, shapeList, selectList);
		}
		else if (appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.MOVE) { 
			command = new MoveCommand(shapeBuilt, shapeList, selectList);
		}
		command.run();
	}
}