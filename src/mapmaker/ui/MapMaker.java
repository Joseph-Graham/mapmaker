package mapmaker.ui;


import javafx.scene.layout.AnchorPane;
import mapmaker.ui.MouseLocator;
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
import java.util.ArrayList;


public class MapMaker extends Application {

    @Override
    public void start(Stage primaryStage) {

        Grid grid = new Grid();
        int cellSize = grid.getCellDimensions();
        int roomWidth = cellSize/2;
        int roomHeight = roomWidth;
        int gridXLength = grid.getWidth();
        int gridYLength = grid.getLength();
        int mapViewXInPixels = cellSize*gridXLength;
        int mapViewYInPixels = cellSize*gridYLength;
        int paddingOffset = roomWidth/2;

        int exitMarkerSize = cellSize/10;
        int exitMarkerOffset = exitMarkerSize/2;
        int exitMarkerSpacing = (roomWidth/2);

        ArrayList<Room> rooms = new ArrayList<>();
        Map map = new Map(grid, rooms);

        primaryStage.setTitle("MapMaker");

        Group root = new Group();
        AnchorPane roomDisplay = new AnchorPane();
        Canvas RoomMap = new Canvas(cellSize*gridXLength, cellSize*gridYLength);
        Canvas LinkMap = new Canvas(cellSize*gridXLength, cellSize*gridYLength);
        Canvas FeedbackLayer = new Canvas(cellSize*gridXLength, cellSize*gridYLength);
        Canvas ControlLayer = new Canvas(cellSize*gridXLength, cellSize*gridYLength);



        GraphicsContext roomsLayer = RoomMap.getGraphicsContext2D();
        roomsLayer.setFill(Color.BLACK);
        roomsLayer.setStroke(Color.BLACK);
        roomsLayer.setLineWidth(2);

        GraphicsContext linksLayer = LinkMap.getGraphicsContext2D();
        linksLayer.setFill(Color.BLACK);
        linksLayer.setStroke(Color.BLACK);
        linksLayer.setLineWidth(2);

        GraphicsContext feedBackLayer = LinkMap.getGraphicsContext2D();
        linksLayer.setFill(Color.YELLOW);
        linksLayer.setStroke(Color.BLACK);
        linksLayer.setGlobalAlpha(0.5);
        linksLayer.setLineWidth(2);




        ControlLayer.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        for (int i = 0; i < map.CurrentRooms.size(); i++){
                            System.out.println("Room at: " + map.CurrentRooms.get(i).getX() +
                                    "," + map.CurrentRooms.get(i).getY());
                        }

                        int gridX = MouseLocator.getMouseGridX(event.getX(), cellSize);
                        int gridY = MouseLocator.getMouseGridY(event.getY(), cellSize);
                        int canvasX = (gridX*cellSize) + paddingOffset;
                        int canvasY = (gridY*cellSize) + paddingOffset;
                        MouseButton button = event.getButton();
                        if (button==MouseButton.PRIMARY) {
                            feedBackLayer.clearRect(0,0, cellSize*gridXLength, cellSize*gridYLength);
                            feedBackLayer.fillRect(canvasX - paddingOffset, canvasY - paddingOffset, cellSize, cellSize);
                            feedBackLayer.strokeOval(canvasX - exitMarkerOffset, canvasY - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //nw, 1
                            feedBackLayer.strokeOval(canvasX + exitMarkerSpacing - exitMarkerOffset, canvasY - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //n. 2
                            feedBackLayer.strokeOval(canvasX + roomWidth - exitMarkerOffset, canvasY - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //ne, 3
                            feedBackLayer.strokeOval(canvasX + roomWidth - exitMarkerOffset, canvasY + exitMarkerSpacing - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //e, 4
                            feedBackLayer.strokeOval(canvasX + roomWidth - exitMarkerOffset, canvasY + roomWidth - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //se, 5
                            feedBackLayer.strokeOval(canvasX + exitMarkerSpacing - exitMarkerOffset, canvasY + roomWidth - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //s, 6
                            feedBackLayer.strokeOval(canvasX - exitMarkerOffset, canvasY + roomWidth - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //sw 7
                            feedBackLayer.strokeOval(canvasX - exitMarkerOffset, canvasY + exitMarkerSpacing - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //w 8
                            feedBackLayer.strokeOval(canvasX + exitMarkerSpacing + exitMarkerOffset/2, canvasY + exitMarkerSpacing - exitMarkerOffset, exitMarkerSize, exitMarkerSize); //u
                            feedBackLayer.strokeOval(canvasX + exitMarkerSpacing - (exitMarkerOffset*2 + exitMarkerOffset/2), canvasY + exitMarkerSpacing - exitMarkerOffset, exitMarkerSize, exitMarkerSize);
                            if(!CheckMapData.roomExistsWhereClicked(gridX, gridY, map)) {
                                Room newRoom = new Room(gridX, gridY);
                                map.addRoom(newRoom);
                                System.out.println(newRoom.getX() + "," + newRoom.getY()); //remove later

                                roomsLayer.strokeRect(canvasX, canvasY, roomWidth, roomHeight);

                            }
                        }
                        if (button==MouseButton.SECONDARY){
                            feedBackLayer.clearRect(0, 0, cellSize * gridXLength, cellSize * gridYLength);
                            if(CheckMapData.roomExistsWhereClicked(gridX, gridY, map)) {
                                map.deleteRoom(gridX, gridY);
                                roomsLayer.clearRect(canvasX, canvasY, roomWidth + 2, roomHeight + 2);
                            }
                        }
                        if (button==MouseButton.MIDDLE){
                            linksLayer.strokeLine(canvasX, canvasY, canvasX+100, canvasY+100);
                        }
                    }
                });
        root.getChildren().add(RoomMap);
        root.getChildren().add(LinkMap);
        root.getChildren().add(ControlLayer);
        root.getChildren().add(roomDisplay);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}