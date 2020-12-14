package mapmaker.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import mapmaker.obj.Map;
import mapmaker.obj.Room;

import java.util.ArrayList;

public class DataPaneDisplayController {
    private Pane dataPane;
    private Map map;
    private MapView mapView;
    private MapViewState mapViewState;
    private Canvas controlCanvas;
    private DataPaneDisplay display;
    private Button update;


    public DataPaneDisplayController(Pane dataPane, MapView mapView, DataPaneDisplay display){
        this.dataPane = dataPane;
        this.mapView = mapView;
        this.controlCanvas = mapView.getControlCanvas();
        this.mapViewState = mapView.getCurrentState();
        this.map = mapView.getMap();
        this.display = display;
        this.update = display.getUpdateRoomFields();
        controlData();
    }
    private void controlData(){
        controlCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseClick) {
                        MouseButton button = mouseClick.getButton();
                        if(button == MouseButton.PRIMARY){
                            if(mapViewState.inSelectionMode()){
                                populateRoomData();
                            }
                            else{
                                clearRoomData();
                            }
                        }
                    }
                });
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(mapViewState.inSelectionMode()) {
                    updateRoomWithNewData();
                }
            }
        });


    }
    private void populateRoomData(){
        Room selectedRoom = map.getRoomAt(mapViewState.getSelectedX(), mapViewState.getSelectedY());
        display.setAttributeFieldText(selectedRoom.getAttributes());
        display.setFlag(selectedRoom.getFlag());
        display.setDescription(selectedRoom.getDesc());

    }
    private void clearRoomData(){
        display.clearData();
    }
    private void updateRoomWithNewData(){
        Room roomToUpdate = map.getRoomAt(mapViewState.getSelectedX(), mapViewState.getSelectedY());
        roomToUpdate.setAttributes(display.getAttributeFieldText());
        roomToUpdate.setDesc(display.getDescriptionFieldText());
        roomToUpdate.setFlag(display.getFlagFieldText());
    }
}
