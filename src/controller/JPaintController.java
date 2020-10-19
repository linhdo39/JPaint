package controller;

import model.interfaces.IApplicationState;
import model.shape.Shape;

import java.util.List;

import controller.command.*;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private List<Shape> shapeList, selectList, copyList;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, List<Shape> shapeList, List<Shape> selectList, List<Shape> copyList) {
    	this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.selectList = selectList;
        this.copyList = copyList;
    }
    
	@Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand(selectList,copyList).run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand(shapeList, selectList, copyList).run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand(shapeList,selectList).run());
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().run());
        uiModule.addEvent(EventName.GROUP, () -> new GroupCommand(shapeList,selectList).run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UngroupCommand(shapeList,selectList).run());
    }
}
