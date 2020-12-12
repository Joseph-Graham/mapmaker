package mapmaker.ui;


import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import mapmaker.util.CheckMapData;
import mapmaker.obj.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mapmaker.util.SaveMap;
import org.w3c.dom.css.CSSStyleSheet;

import javax.xml.crypto.Data;


public class MapMaker extends Application {

    @Override
    public void start(Stage primaryStage) {


        Grid grid = new Grid();
        Map map = new Map(grid);
        MapViewParams mapParams = new MapViewParams(grid);
        MapViewState currentState = new MapViewState();

        primaryStage.setTitle("MapMaker");

        //Group root = new Group();


        Canvas BackgroundColor = new Canvas(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY()); //layer to serve as the background color for the map layers
        Canvas LinkCanvas = new Canvas(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY());
        Canvas RoomCanvas = new Canvas(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY());
        Canvas ExitMarkerCanvas = new Canvas(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY());
        Canvas FeedbackCanvas = new Canvas(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY());
        Canvas SpecialFeedbackCanvas = new Canvas(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY());
        Canvas GridCanvas = new Canvas(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY());
        Canvas ControlCanvas = new Canvas(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY());

        GraphicsContext background = BackgroundColor.getGraphicsContext2D();

        GraphicsContext linksLayer = LinkCanvas.getGraphicsContext2D();
        linksLayer.setFill(Color.BLACK);
        linksLayer.setStroke(Color.BLACK);
        linksLayer.setLineWidth(2);

        GraphicsContext roomsLayer = RoomCanvas.getGraphicsContext2D();
        roomsLayer.setFill(Color.BLACK);
        roomsLayer.setStroke(Color.BLACK);
        roomsLayer.setLineWidth(2);

        GraphicsContext exitMarkerLayer = ExitMarkerCanvas.getGraphicsContext2D();
        exitMarkerLayer.setFill(Color.BLACK);
        exitMarkerLayer.setStroke(Color.BLACK);
        exitMarkerLayer.setLineWidth(2);

        GraphicsContext highlightLayer = FeedbackCanvas.getGraphicsContext2D();
        highlightLayer.setStroke(Color.BLACK);
        highlightLayer.setLineWidth(2);

        GraphicsContext specialHighlightLayer = SpecialFeedbackCanvas.getGraphicsContext2D();
        specialHighlightLayer.setStroke(Color.BLACK);
        specialHighlightLayer.setGlobalAlpha(0.5);
        specialHighlightLayer.setLineWidth(2);

        GraphicsContext gridLayer = GridCanvas.getGraphicsContext2D();

        MapViewRenderer mapViewRenderer = new MapViewRenderer(mapParams, roomsLayer, linksLayer, exitMarkerLayer, highlightLayer, specialHighlightLayer, gridLayer, background, map);

        mapViewRenderer.drawGrid();
        ControlCanvas.addEventHandler(MouseEvent.MOUSE_MOVED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseMoved) {
                        if(currentState.inLinkExitSelectionMode()){
                            int hoverGridX = MouseLocator.getMouseGridX(mouseMoved.getX(), mapParams.getCellDimensions());
                            int hoverGridY = MouseLocator.getMouseGridY(mouseMoved.getY(), mapParams.getCellDimensions());
                            if((CheckMapData.roomExistsAtMouseLocation(hoverGridX, hoverGridY, map)
                            )&&(!currentState.roomHoveredIsSelected(hoverGridX, hoverGridY))){
                                mapViewRenderer.highlightRoomHovered(hoverGridX, hoverGridY);
                                mapViewRenderer.drawExitMarkersDisplayAtHover(hoverGridX, hoverGridY);

                                int direction = MouseLocator.getExitLinkSelected(mouseMoved.getX(), mouseMoved.getY(), mapParams);
                                if (direction != 0){
                                    mapViewRenderer.fillExitMarkerSelection(hoverGridX, hoverGridY, direction);

                                }

                            }
                            else{
                                mapViewRenderer.clearHoverHighlights();
                            }
                        }
                    }
                });

        ControlCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        int gridX = MouseLocator.getMouseGridX(event.getX(), mapParams.getCellDimensions());
                        int gridY = MouseLocator.getMouseGridY(event.getY(), mapParams.getCellDimensions());
                        //int gridXPixels = gridX * mapParams.getCellDimensions();
                        //int gridYPixels = gridY * mapParams.getCellDimensions();

                        MouseButton button = event.getButton();
                        if (button == MouseButton.PRIMARY) {
                            if (currentState.inLinkExitSelectionMode()) {
                                /*if ((CheckMapData.roomExistsAtMouseLocation(gridX, gridY, map))
                                        &&
                                     {*/

                                    int direction = MouseLocator.getExitLinkSelected(event.getX(), event.getY(), mapParams);

                                    if ((direction != 0) && (!currentState.roomHoveredIsSelected(gridX, gridY))) {
                                        System.out.println(gridX + " " + gridY);
                                        System.out.println(direction);
                                        currentState.secondLinkHasBeenSelected(gridX, gridY, direction);

                                        Link link = new Link(currentState.getLinkRoomOneX(), currentState.getLinkRoomOneY(),
                                                currentState.getLinkRoomTwoX(), currentState.getLinkRoomTwoY(),
                                                currentState.getLinkRoomOneDirection(), currentState.getLinkRoomTwoDirection());
                                        map.addLink(link);
                                        currentState.clearLinkSelection();
                                        currentState.clearRoomSelections();
                                        mapViewRenderer.drawLinks();
                                        mapViewRenderer.clearHoverHighlights();
                                        mapViewRenderer.clearHighlights();
                                        mapViewRenderer.fillExistingExitMarkers();
                                        System.out.println("Link created...");
                                    } else {
                                        currentState.clearLinkSelection();
                                        //currentState.clearRoomSelections();
                                        mapViewRenderer.clearHoverHighlights();
                                        mapViewRenderer.highlightRoom(currentState.getSelectedX(), currentState.getSelectedY());
                                        mapViewRenderer.drawExitMarkersDisplay(currentState.getSelectedX(), currentState.getSelectedY());
                                        System.out.println("No external exit point selected.");
                                        System.out.println("Link selection state cleared.");
                                    }
                            } else if (currentState.inSelectionMode()) {
                                System.out.println("Currently in selection mode.");
                                if /*((CheckMapData.roomExistsAtMouseLocation(gridX, gridY, map)
                                        && */((gridX == currentState.getSelectedX() && gridY == currentState.getSelectedY()))/*))*/ {
                                    int direction = MouseLocator.getExitLinkSelected(event.getX(), event.getY(), mapParams);
                                    if (direction != 0) {
                                        System.out.println(direction);
                                        mapViewRenderer.toggleHighlightRoom(gridX, gridY);
                                        mapViewRenderer.toggleExitMarkerSelection(gridX, gridY, direction);
                                        currentState.firstlinkHasBeenSelected(gridX, gridY, direction);
                                        }
                                    } else if ((CheckMapData.roomExistsAtMouseLocation(gridX, gridY, map))) {

                                        mapViewRenderer.highlightRoom(gridX, gridY);
                                        mapViewRenderer.drawExitMarkersDisplay(gridX, gridY);
                                        currentState.roomHasBeenSelected(gridX, gridY);
                                    } else {
                                        mapViewRenderer.clearHighlights();
                                        currentState.clearRoomSelections();
                                    }
                            } else if (CheckMapData.roomExistsAtMouseLocation(gridX, gridY, map)) {
                                mapViewRenderer.highlightRoom(gridX, gridY);
                                mapViewRenderer.drawExitMarkersDisplay(gridX, gridY);
                                currentState.roomHasBeenSelected(gridX, gridY);
                                System.out.println("Highlighted room at" + gridX + " " + gridY);

                            } else {
                                Room newRoom = new Room(gridX, gridY);
                                map.addRoom(newRoom);
                                mapViewRenderer.drawRoom(gridX, gridY);
                                mapViewRenderer.clearHighlights();
                                System.out.println(newRoom.getX() + "," + newRoom.getY());
                            }
                        }
                    if (button == MouseButton.SECONDARY) {
                            mapViewRenderer.clearHighlights();
                            currentState.clearRoomSelections();
                            currentState.clearLinkSelection();
                            if (CheckMapData.roomExistsAtMouseLocation(gridX, gridY, map)) {
                                map.deleteRoom(gridX, gridY);
                                mapViewRenderer.clearLinks();
                                mapViewRenderer.removeRoom(gridX, gridY);
                                mapViewRenderer.drawLinks();
                                mapViewRenderer.clearExistingExitMarkers();
                                mapViewRenderer.fillExistingExitMarkers();
                            }
                        }
                    if (button == MouseButton.MIDDLE) {
                        SaveMap.saveToJson(map, grid);
                        }
                    }
                });

        /*DataPane.setCenter(LinkCanvas);
        DataPane.setCenter(RoomCanvas);
        DataPane.setCenter(FeedbackCanvas);
        DataPane.setCenter(SpecialFeedbackCanvas);
        DataPane.setCenter(ExitMarkerCanvas);
        DataPane.setCenter(GridCanvas);
        DataPane.setCenter(ControlCanvas);*/

        Pane basePane = new Pane();
        Pane topButtonPane = new Pane();
        Pane dataPane = new Pane();
        Pane mapContrastBackground = new Pane();
        Pane mapView = new Pane();

        basePane.getChildren().add(mapContrastBackground);
        basePane.getChildren().add(topButtonPane);
        basePane.getChildren().add(dataPane);
        mapContrastBackground.getChildren().add(mapView);

        mapView.getChildren().add(BackgroundColor);
        mapView.getChildren().add(GridCanvas);
        mapView.getChildren().add(LinkCanvas);
        mapView.getChildren().add(RoomCanvas);
        mapView.getChildren().add(FeedbackCanvas);
        mapView.getChildren().add(SpecialFeedbackCanvas);
        mapView.getChildren().add(ExitMarkerCanvas);
        mapView.getChildren().add(ControlCanvas);

        basePane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        basePane.setMinHeight(mapContrastBackground.getMinHeight() + 50);
        basePane.setMinWidth(mapParams.getTotalCanvasX() + mapParams.getCellDimensions() + 300);

        topButtonPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        topButtonPane.setMinHeight(50);
        topButtonPane.setMinWidth(mapParams.getTotalCanvasX() + mapParams.getCellDimensions());
        topButtonPane.relocate(0,0);

        dataPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
        dataPane.setMinWidth(280);
        dataPane.setMinHeight(mapParams.getTotalCanvasY() + mapParams.getCellDimensions());
        dataPane.relocate(mapParams.getTotalCanvasX() + mapParams.getCellDimensions() + 10, 25);

        TextField attributeField = new TextField();
        dataPane.getChildren().add(attributeField);
        attributeField.relocate(50, 300);

        /*for(int i = 0; i <= 10; i++) {

        }*/
        mapContrastBackground.relocate(0, 50);
        mapContrastBackground.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        mapContrastBackground.setMinSize(mapParams.getTotalCanvasX() + mapParams.getCellDimensions(), mapParams.getTotalCanvasY() + mapParams.getCellDimensions());

        mapView.relocate(mapParams.getCellDimensions()/2, mapParams.getCellDimensions()/2);
        mapView.setMinSize(mapParams.getTotalCanvasX(), mapParams.getTotalCanvasY());

        MenuBar topMenuBar = new MenuBar();
        Menu topMenuBar_File = new Menu("File");
        MenuItem topMenuBar_File_New = new MenuItem("New");
        MenuItem topMenuBar_File_Load = new MenuItem("Load");
        MenuItem topMenuBar_File_Save = new MenuItem("Save");
        Menu topMenuBar_Edit = new Menu("Edit");
        MenuItem topMenuBar_Edit_Clear_links = new MenuItem("Clear links");
        MenuItem topMenuBar_Edit_Clear_rooms = new MenuItem("Clear rooms");

        topMenuBar_File.getItems().addAll(topMenuBar_File_New, topMenuBar_File_Load, topMenuBar_File_Save);
        topMenuBar.getMenus().add(topMenuBar_File);

        topMenuBar_Edit.getItems().addAll(topMenuBar_Edit_Clear_links, topMenuBar_Edit_Clear_rooms);
        topMenuBar.getMenus().add(topMenuBar_Edit);

        basePane.getChildren().add(topMenuBar);

        topMenuBar.setMinWidth(mapParams.getTotalCanvasX() + mapParams.getCellDimensions() + 300);

        Scene scene = new Scene(basePane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}