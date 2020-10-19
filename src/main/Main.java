package main;

import java.util.ArrayList;
import java.util.List;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseHandler;
import model.persistence.ApplicationState;
import model.shape.Shape;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.gui.PaintCanvasSingleton;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        List<Shape> copyList = new ArrayList<Shape>();
        List<Shape> shapeList = new ArrayList<Shape>();
    	List<Shape> selectList = new ArrayList<Shape>();
    	PaintCanvasSingleton.setGraphics(paintCanvas);
        IJPaintController controller = new JPaintController(uiModule, appState,shapeList,selectList,copyList);
        controller.setup();
        
        paintCanvas.addMouseListener(new MouseHandler(appState,shapeList, selectList));

    }
}
