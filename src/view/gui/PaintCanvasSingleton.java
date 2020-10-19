package view.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import view.interfaces.PaintCanvasBase;

public class PaintCanvasSingleton {
	private static PaintCanvasSingleton instance;
	private static Graphics2D graphics;
	private static PaintCanvasBase paintCanvas;
	private PaintCanvasSingleton(PaintCanvasBase paintCanvas) {
		PaintCanvasSingleton.paintCanvas = paintCanvas;
		graphics = paintCanvas.getGraphics2D();
	}
	
	public static void setGraphics(PaintCanvasBase paintCanvas) {
		if(instance == null)
			instance = new PaintCanvasSingleton(paintCanvas);
	}
	
	public static Graphics2D getGraphics() {
		return graphics;
	}
	
	public static void clearCanvas() {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());
	}
}
