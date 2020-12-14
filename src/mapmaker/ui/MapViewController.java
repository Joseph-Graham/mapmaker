package mapmaker.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import mapmaker.obj.Link;
import mapmaker.obj.Map;
import mapmaker.obj.Room;

import javax.xml.crypto.Data;

public class MapViewController {
    private MapView CurrentView;
    private Canvas ControlCanvas;
    private MapViewState currentState;
    private MapViewParams mapParams;
    private MapViewRenderer renderer;
    private MouseLocator mouseLocator;
    private Map map;
    private int hoverX;
    private int hoverY;
    private int clickedX;
    private int clickedY;
    private int directionClicked;
    private int directionHovered;
    public MapViewController(MapView mapView, MapViewParams mapParams, MapViewState currentState){
        //central object to control map view state, objects rendered on screen, and manipulation of map data
        this.CurrentView = mapView;
        this.currentState = currentState;
        this.mapParams = mapParams;
        this.ControlCanvas = mapView.getControlCanvas();
        this.map = mapView.getMap();
        this.mouseLocator = new MouseLocator(map, mapParams);
        renderer = new MapViewRenderer(mapParams, CurrentView.getMap(), mapView);
        ControlView();
    }

    public void ControlView() {
        ControlCanvas.addEventHandler(MouseEvent.MOUSE_MOVED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseMoved) {
                        if (currentState.inLinkExitSelectionMode()) {
                            setHoverLocation(mouseMoved);
                            setExitDirectionAtHoverLocation(mouseMoved);
                            highlightAnyRoomHoveredOver();
                        }
                    }
                });

        ControlCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        setClickLocation(event);
                        setExitDirectionAtClickLocation(event);
                        MouseButton button = event.getButton();
                        if (button == MouseButton.PRIMARY) {
                            if (currentState.inLinkExitSelectionMode()) {
                                determineIfLinkCreatedAndPerformUpdates();
                            }
                            else if (currentState.inSelectionMode()) {
                                selectRoomDeselectRoomOrEnterExitSelectionMode();
                            }
                            else {
                                enterSelectionModeOrCreateANewRoom();
                            }
                        }
                        else if (button == MouseButton.SECONDARY) {
                            exitSelectionModeAndOrDeleteARoom();
                        }
                    }
                });
        }
        private void setHoverLocation(MouseEvent mouseMoved){
            this.hoverX = mouseLocator.getMouseGridX(mouseMoved.getX());
            this.hoverY = mouseLocator.getMouseGridY(mouseMoved.getY());
        }
        private void setClickLocation(MouseEvent mouseClick){
            this.clickedX = mouseLocator.getMouseGridX(mouseClick.getX());
            this.clickedY = mouseLocator.getMouseGridY(mouseClick.getY());
        }
        private void setExitDirectionAtClickLocation(MouseEvent mouseClick){
            this.directionClicked = mouseLocator.getExitLinkSelected(mouseClick.getX(), mouseClick.getY());
        }
        private void setExitDirectionAtHoverLocation(MouseEvent mouseHover){
            this.directionHovered = mouseLocator.getExitLinkSelected(mouseHover.getX(), mouseHover.getY());
        }
        private boolean exitWasSelected(){
            if (directionClicked == 0){
                return false;
            }
            return true;
        }
        private boolean exitSelectedWasAtCurrentRoom(){
            if(currentState.roomHoveredIsSelected(clickedX, clickedY)){
                return true;
            }
            return false;
        }
        private void highlightAnyRoomHoveredOver(){

            if ((mouseLocator.roomExistsAtMouseLocation(hoverX, hoverY)
            ) && (!currentState.roomHoveredIsSelected(hoverX, hoverY))) {
                renderer.highlightRoomHovered(hoverX, hoverY);
                renderer.drawExitMarkersDisplayAtHover(hoverX, hoverY);
                if (directionHovered != 0) {
                    renderer.fillExitMarkerSelection(hoverX, hoverY, directionHovered);
                }
            } else {
                renderer.clearHoverHighlights();
            }
        }
        private void determineIfLinkCreatedAndPerformUpdates(){

            if (exitWasSelected() && exitSelectedWasAtCurrentRoom() == false && mouseLocator.roomExistsAtMouseLocation(clickedX, clickedY)) {
                createANewLink();
                //System.out.println(clickedX + " " + clickedY);
                //System.out.println(directionClicked);
                //System.out.println("Link created...");
            } else {
                returnToRoomSelection();
                //System.out.println("No external exit point selected.");
                //System.out.println("Link selection state cleared.");
            }
        }
        private void selectRoomDeselectRoomOrEnterExitSelectionMode(){
            if (exitWasSelected() && exitSelectedWasAtCurrentRoom()) {
                    //System.out.println("Selected exit " + directionClicked + " at the current room.");
                    enterLinkExitSelectionMode();
            } else if (mouseLocator.roomExistsAtMouseLocation(clickedX, clickedY)) {
                    enterSelectionMode();
            } else {
                    exitSelectionMode();
            }
        }
        private void enterSelectionModeOrCreateANewRoom(){
            if(mouseLocator.roomExistsAtMouseLocation(clickedX, clickedY)){
                enterSelectionMode();
            }
            else{
                createANewRoom();
            }
        }
        private void exitSelectionModeAndOrDeleteARoom(){
            exitSelectionMode();
            if (mouseLocator.roomExistsAtMouseLocation(clickedX, clickedY)) {
                deleteARoom();
            }
        }
        private void createANewLink(){
            markTheSecondExit();
            performLinkDataUpdate();
            clearMapSelectionStates();
            updateMapViewWithNewLinks();
            exitSelectionMode();
        }
        private void performLinkDataUpdate(){
            Link link = new Link(currentState.getLinkRoomOneX(),
                    currentState.getLinkRoomOneY(),
                    currentState.getLinkRoomTwoX(),
                    currentState.getLinkRoomTwoY(),
                    currentState.getLinkRoomOneDirection(),
                    currentState.getLinkRoomTwoDirection());

            map.addLink(link);
        }
        private void markTheSecondExit(){
            currentState.secondExitHasBeenSelected(clickedX, clickedY, directionClicked);
        }
        private void clearMapSelectionStates(){
            currentState.clearLinkSelection();
            currentState.clearRoomSelections();
        }
        private void updateMapViewWithNewLinks(){
            renderer.drawLinks();
            renderer.fillExistingExitMarkers();
        }
        private void returnToRoomSelection(){
            currentState.clearLinkSelection();
            renderer.clearHoverHighlights();
            renderer.highlightRoom(currentState.getSelectedX(), currentState.getSelectedY());
            renderer.drawExitMarkersDisplay(currentState.getSelectedX(), currentState.getSelectedY());
        }
        private void enterSelectionMode(){
            currentState.roomHasBeenSelected(clickedX, clickedY);
            renderer.highlightRoom(currentState.getSelectedX(), currentState.getSelectedY());
            renderer.drawExitMarkersDisplay(currentState.getSelectedX(), currentState.getSelectedY());
        }
        private void exitSelectionMode(){
            currentState.clearRoomSelections();
            currentState.clearLinkSelection();
            renderer.clearHoverHighlights();
            renderer.clearHighlights();
        }
        private void enterLinkExitSelectionMode(){
            currentState.firstExitHasBeenSelected(clickedX, clickedY, directionClicked);
            renderer.toggleHighlightRoom(clickedX, clickedY);
            renderer.toggleExitMarkerSelection(clickedX, clickedY, directionClicked);
        }
        private void createANewRoom(){
            Room newRoom = new Room(clickedX, clickedY);
            map.addRoom(newRoom);
            renderer.drawRooms();
            renderer.clearHighlights();
            //System.out.println("New room at: " + newRoom.getX() + "," + newRoom.getY());
        }
        private void deleteARoom(){
            map.deleteRoom(clickedX, clickedY);
            renderer.clearLinks();
            //renderer.removeRoom(clickedX, clickedY);
            renderer.drawRooms();
            renderer.drawLinks();
            renderer.clearExistingExitMarkers();
            renderer.fillExistingExitMarkers();
        }
        private void toggleGrid(){

        }
        public void refreshMapView(){
            renderer.drawRooms();
            renderer.drawLinks();
            renderer.fillExistingExitMarkers();
        }
}
