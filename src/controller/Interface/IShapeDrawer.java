package controller.Interface;

import model.shape.Shape;
import model.shape.ShapeConstructor;

public interface IShapeDrawer {
	void execute();
	void move(int a, int b);
	Shape paste(ShapeConstructor built, int offsetX);
}
