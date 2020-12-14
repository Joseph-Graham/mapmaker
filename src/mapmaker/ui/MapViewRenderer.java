package mapmaker.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import mapmaker.obj.Link;
import mapmaker.obj.Map;
import mapmaker.obj.Room;

public class MapViewRenderer {

    private Map map;
    private MapViewParams params;
    private GraphicsContext roomsLayer;
    private GraphicsContext linksLayer;
    private GraphicsContext exitMarkerLayer;
    private GraphicsContext highlightLayer;
    private GraphicsContext specialHighlightLayer;
    private GraphicsContext gridLayer;
    private GraphicsContext background;
    private int totalCanvasX;
    private int totalCanvasY;
    private int cellSize;
    private int paddingOffset;
    private int roomWidth;
    private int roomHeight;

    public MapViewRenderer(MapViewParams params, Map map, MapView mapView){
        //procedurally draws to the various GraphicsContexts
        //when called from MapViewController
        //to render the current map view
        //methods take grid coordinates (as opposed to pixel coordinates) and calculate the pixel coordinates where needed
        this.map = map;
        this.params = params;
        this.roomsLayer = mapView.getRoomsLayer();
        this.linksLayer = mapView.getLinksLayer();
        this.exitMarkerLayer = mapView.getExitMarkerLayer();
        this.highlightLayer = mapView.getHighlightLayer();
        this.specialHighlightLayer = mapView.getSpecialHighlightLayer();
        this.gridLayer = mapView.getGridLayer();
        this.background = mapView.getBackground();

        this.totalCanvasX = params.getTotalMapViewX();
        this.totalCanvasY = params.getTotalMapViewY();
        this.cellSize = params.getCellDimensions();
        this.paddingOffset = params.getPaddingOffset();
        this.roomWidth = params.getRoomWidth();
        this.roomHeight = params.getRoomHeight();
        drawGrid();
        drawBackground();
    }
    public void drawBackground(){ //paints the background layer
        background.setFill(Color.WHITESMOKE);
        background.setGlobalAlpha(0.8);
        background.fillRect(0,0, totalCanvasX, totalCanvasY);
    }
    public void highlightRoom(int X, int Y){ //highlights the room selected
        highlightLayer.setStroke(Color.BLACK);
        highlightLayer.setLineWidth(2);
        int pixelsX = X*cellSize;
        int pixelsY = Y*cellSize;
        highlightLayer.setGlobalAlpha(0.5);
        highlightLayer.setFill(Color.LIGHTGREEN);
        clearHighlights();
        highlightLayer.fillRect(pixelsX, pixelsY, cellSize, cellSize);
    }
    public void toggleHighlightRoom(int X, int Y){ //toggles the highlight on the currently selected after entering link exit selection mode
        int pixelsX = X*cellSize;
        int pixelsY = Y*cellSize;
        specialHighlightLayer.setFill(Color.LIGHTGREEN);
        specialHighlightLayer.fillRect(pixelsX, pixelsY, cellSize, cellSize);
    }
    public void clearHighlights(){
        highlightLayer.clearRect(0, 0, totalCanvasX, totalCanvasY);
    }
    public void highlightRoomHovered(int X, int Y){ //given the grid X and Y, highlights the room the user is hovering the mouse over when in link selection mode
        int pixelsX = X*cellSize;
        int pixelsY = Y*cellSize;
        specialHighlightLayer.setFill(Color.LIGHTGREEN);
        specialHighlightLayer.clearRect(0,0,totalCanvasX,totalCanvasY);
        specialHighlightLayer.fillRect(pixelsX, pixelsY, cellSize, cellSize);
    }
    public void clearHoverHighlights(){
        specialHighlightLayer.clearRect(0,0, totalCanvasX, totalCanvasY);
    }
    public void drawRooms(){
        roomsLayer.clearRect(0,0, totalCanvasX, totalCanvasY);
        roomsLayer.setStroke(Color.BLACK);
        roomsLayer.setLineWidth(2);
        for(Room i : map.getCurrentRooms()){
            int pixelsX = i.getX()*cellSize;
            int pixelsY = i.getY()*cellSize;
            roomsLayer.setFill(i.getColor());
            roomsLayer.strokeRect(pixelsX + paddingOffset, pixelsY + paddingOffset, roomWidth, roomHeight);
            roomsLayer.fillRect(pixelsX + paddingOffset + 1, pixelsY + paddingOffset + 1, roomWidth - 2, roomHeight - 2);
        }
    }
  /*  public void drawRoom(int X, int Y){
        roomsLayer.setFill(Color.BLACK);
        roomsLayer.setStroke(Color.BLACK);
        roomsLayer.setLineWidth(2);
        int pixelsX = X*cellSize;
        int pixelsY = Y*cellSize;
        roomsLayer.setFill(Color.WHITE);
        roomsLayer.strokeRect(pixelsX + paddingOffset, pixelsY + paddingOffset, roomWidth, roomHeight);
        roomsLayer.fillRect(pixelsX + paddingOffset + 1, pixelsY + paddingOffset + 1, roomWidth - 2, roomHeight - 2);
    }*/
    public void drawExitMarkersDisplay(int X, int Y){
        //draws the exit markers display to the normal highlight layer for the selected room for any unlinked exits
        //given the X in Y grid coordinates of the room
        int marker = params.getExitMarkerSize();
        highlightLayer.strokeOval(params.get_NW_ExitX(X), params.get_NW_ExitY(Y), marker, marker); //nw, 1
        highlightLayer.strokeOval(params.get_N_ExitX(X), params.get_N_ExitY(Y), marker, marker); //n. 2
        highlightLayer.strokeOval(params.get_NE_ExitX(X), params.get_NE_ExitY(Y), marker, marker); //ne, 3
        highlightLayer.strokeOval(params.get_E_ExitX(X), params.get_E_ExitY(Y), marker, marker); //e, 4
        highlightLayer.strokeOval(params.get_SE_ExitX(X), params.get_SE_ExitY(Y), marker, marker); //se, 5
        highlightLayer.strokeOval(params.get_S_ExitX(X), params.get_S_ExitY(Y), marker, marker); //s, 6
        highlightLayer.strokeOval(params.get_SW_ExitX(X), params.get_SW_ExitY(Y), marker, marker); //sw 7
        highlightLayer.strokeOval(params.get_W_ExitX(X), params.get_W_ExitY(Y), marker, marker); //w 8
        highlightLayer.strokeOval(params.get_U_ExitX(X), params.get_U_ExitY(Y), marker, marker); //u 9
        highlightLayer.strokeOval(params.get_D_ExitX(X),params.get_D_ExitY(Y), marker, marker); //d 10
    }
    public void drawExitMarkersDisplayAtHover(int X, int Y){
        //draws the exit markers to the special highlight layer when in link selection mode for any unlinked exits
        //given the X in Y grid coordinates of the room
        specialHighlightLayer.setStroke(Color.BLACK);
        specialHighlightLayer.setGlobalAlpha(0.5);
        specialHighlightLayer.setLineWidth(2);
        int marker = params.getExitMarkerSize();
        specialHighlightLayer.strokeOval(params.get_NW_ExitX(X), params.get_NW_ExitY(Y), marker, marker); //nw, 1
        specialHighlightLayer.strokeOval(params.get_N_ExitX(X), params.get_N_ExitY(Y), marker, marker); //n. 2
        specialHighlightLayer.strokeOval(params.get_NE_ExitX(X), params.get_NE_ExitY(Y), marker, marker); //ne, 3
        specialHighlightLayer.strokeOval(params.get_E_ExitX(X), params.get_E_ExitY(Y), marker, marker); //e, 4
        specialHighlightLayer.strokeOval(params.get_SE_ExitX(X), params.get_SE_ExitY(Y), marker, marker); //se, 5
        specialHighlightLayer.strokeOval(params.get_S_ExitX(X), params.get_S_ExitY(Y), marker, marker); //s, 6
        specialHighlightLayer.strokeOval(params.get_SW_ExitX(X), params.get_SW_ExitY(Y), marker, marker); //sw 7
        specialHighlightLayer.strokeOval(params.get_W_ExitX(X), params.get_W_ExitY(Y), marker, marker); //w 8
        specialHighlightLayer.strokeOval(params.get_U_ExitX(X), params.get_U_ExitY(Y), marker, marker); //u 9
        specialHighlightLayer.strokeOval(params.get_D_ExitX(X),params.get_D_ExitY(Y), marker, marker); //d 10
    }
    public void fillExitMarkerSelection(int X, int Y, int direction){
        int marker = params.getExitMarkerSize();
        specialHighlightLayer.setFill(Color.YELLOW);
        if(direction==1){
            specialHighlightLayer.fillOval(params.get_NW_ExitX(X), params.get_NW_ExitY(Y), marker, marker); //nw, 1
        }
        else if(direction==2){
            specialHighlightLayer.fillOval(params.get_N_ExitX(X), params.get_N_ExitY(Y), marker, marker); //n. 2
        }
        else if(direction==3){
            specialHighlightLayer.fillOval(params.get_NE_ExitX(X), params.get_NE_ExitY(Y), marker, marker); //ne, 3
        }
        else if (direction==4){
            specialHighlightLayer.fillOval(params.get_E_ExitX(X), params.get_E_ExitY(Y), marker, marker); //e, 4
        }
        else if (direction==5){
            specialHighlightLayer.fillOval(params.get_SE_ExitX(X), params.get_SE_ExitY(Y), marker, marker); //se, 5
        }
        else if (direction==6){
            specialHighlightLayer.fillOval(params.get_S_ExitX(X), params.get_S_ExitY(Y), marker, marker); //s, 6
        }
        else if (direction==7){
            specialHighlightLayer.fillOval(params.get_SW_ExitX(X), params.get_SW_ExitY(Y), marker, marker); //sw 7
        }
        else if (direction==8){
            specialHighlightLayer.fillOval(params.get_W_ExitX(X), params.get_W_ExitY(Y), marker, marker); //w 8
        }
        else if (direction==9){
            specialHighlightLayer.fillOval(params.get_U_ExitX(X), params.get_U_ExitY(Y), marker, marker); //u 9
        }
        else if (direction==10) {
            specialHighlightLayer.fillOval(params.get_D_ExitX(X), params.get_D_ExitY(Y), marker, marker); //d 10
        }
    }
    public void toggleExitMarkerSelection(int X, int Y, int direction){
        highlightLayer.setFill(Color.LIGHTYELLOW);
        highlightLayer.setGlobalAlpha(1.0);
        int marker = params.getExitMarkerSize();
        if(direction==1){
            highlightLayer.fillOval(params.get_NW_ExitX(X), params.get_NW_ExitY(Y), marker, marker); //nw, 1
        }
        else if(direction==2){
            highlightLayer.fillOval(params.get_N_ExitX(X), params.get_N_ExitY(Y), marker, marker); //n. 2
        }
        else if(direction==3){
            highlightLayer.fillOval(params.get_NE_ExitX(X), params.get_NE_ExitY(Y), marker, marker); //ne, 3
        }
        else if (direction==4){
            highlightLayer.fillOval(params.get_E_ExitX(X), params.get_E_ExitY(Y), marker, marker); //e, 4
        }
        else if (direction==5){
            highlightLayer.fillOval(params.get_SE_ExitX(X), params.get_SE_ExitY(Y), marker, marker); //se, 5
        }
        else if (direction==6){
            highlightLayer.fillOval(params.get_S_ExitX(X), params.get_S_ExitY(Y), marker, marker); //s, 6
        }
        else if (direction==7){
            highlightLayer.fillOval(params.get_SW_ExitX(X), params.get_SW_ExitY(Y), marker, marker); //sw 7
        }
        else if (direction==8){
            highlightLayer.fillOval(params.get_W_ExitX(X), params.get_W_ExitY(Y), marker, marker); //w 8
        }
        else if (direction==9){
            highlightLayer.fillOval(params.get_U_ExitX(X), params.get_U_ExitY(Y), marker, marker); //u 9
        }
        else if (direction==10) {
            highlightLayer.fillOval(params.get_D_ExitX(X), params.get_D_ExitY(Y), marker, marker); //d 10
        }
    }
    public void clearExistingExitMarkers(){
        exitMarkerLayer.clearRect(0,0, totalCanvasX, totalCanvasY);
    }
    public void fillExistingExitMarkers(){
        exitMarkerLayer.setFill(Color.BLACK);
        exitMarkerLayer.setStroke(Color.BLACK);
        exitMarkerLayer.setLineWidth(2);
        for(Link i : map.getCurrentLinks()) {
            int marker = params.getExitMarkerSize();
            int direction1 = i.getRoomOneDirection();
            int direction2 = i.getRoomTwoDirection();
            int X1 = i.getRoomOneX();
            int Y1 = i.getRoomOneY();
            int X2 = i.getRoomTwoX();
            int Y2 = i.getRoomTwoY();
            exitMarkerLayer.setFill(Color.GREY);
            if (direction1 == 1) {
                exitMarkerLayer.fillOval(params.get_NW_ExitX(X1), params.get_NW_ExitY(Y1), marker, marker);
            } else if (direction1 == 2) {
                exitMarkerLayer.fillOval(params.get_N_ExitX(X1), params.get_N_ExitY(Y1), marker, marker); //n. 2
            } else if (direction1 == 3) {
                exitMarkerLayer.fillOval(params.get_NE_ExitX(X1), params.get_NE_ExitY(Y1), marker, marker); //ne, 3
            } else if (direction1 == 4) {
                exitMarkerLayer.fillOval(params.get_E_ExitX(X1), params.get_E_ExitY(Y1), marker, marker); //e, 4
            } else if (direction1 == 5) {
                exitMarkerLayer.fillOval(params.get_SE_ExitX(X1), params.get_SE_ExitY(Y1), marker, marker); //se, 5
            } else if (direction1 == 6) {
                exitMarkerLayer.fillOval(params.get_S_ExitX(X1), params.get_S_ExitY(Y1), marker, marker); //s, 6
            } else if (direction1 == 7) {
                exitMarkerLayer.fillOval(params.get_SW_ExitX(X1), params.get_SW_ExitY(Y1), marker, marker); //sw 7
            } else if (direction1 == 8) {
                exitMarkerLayer.fillOval(params.get_W_ExitX(X1), params.get_W_ExitY(Y1), marker, marker); //w 8
            } else if (direction1 == 9) {
                exitMarkerLayer.fillOval(params.get_U_ExitX(X1), params.get_U_ExitY(Y1), marker, marker); //u 9
            } else if (direction1 == 10) {
                exitMarkerLayer.fillOval(params.get_D_ExitX(X1), params.get_D_ExitY(Y1), marker, marker); //d 10
            }
            if (direction2 == 1) {
                exitMarkerLayer.fillOval(params.get_NW_ExitX(X2), params.get_NW_ExitY(Y2), marker, marker);
            } else if (direction2 == 2) {
                exitMarkerLayer.fillOval(params.get_N_ExitX(X2), params.get_N_ExitY(Y2), marker, marker); //n. 2
            } else if (direction2 == 3) {
                exitMarkerLayer.fillOval(params.get_NE_ExitX(X2), params.get_NE_ExitY(Y2), marker, marker); //ne, 3
            } else if (direction2 == 4) {
                exitMarkerLayer.fillOval(params.get_E_ExitX(X2), params.get_E_ExitY(Y2), marker, marker); //e, 4
            } else if (direction2 == 5) {
                exitMarkerLayer.fillOval(params.get_SE_ExitX(X2), params.get_SE_ExitY(Y2), marker, marker); //se, 5
            } else if (direction2 == 6) {
                exitMarkerLayer.fillOval(params.get_S_ExitX(X2), params.get_S_ExitY(Y2), marker, marker); //s, 6
            } else if (direction2 == 7) {
                exitMarkerLayer.fillOval(params.get_SW_ExitX(X2), params.get_SW_ExitY(Y2), marker, marker); //sw 7
            } else if (direction2 == 8) {
                exitMarkerLayer.fillOval(params.get_W_ExitX(X2), params.get_W_ExitY(Y2), marker, marker); //w 8
            } else if (direction2 == 9) {
                exitMarkerLayer.fillOval(params.get_U_ExitX(X2), params.get_U_ExitY(Y2), marker, marker); //u 9
            } else if (direction2 == 10) {
                exitMarkerLayer.fillOval(params.get_D_ExitX(X2), params.get_D_ExitY(Y2), marker, marker); //d 10
            }
        }
    }
    public void removeRoom(int X, int Y){
        int pixelsX = X*cellSize;
        int pixelsY = Y*cellSize;
        roomsLayer.clearRect(pixelsX + paddingOffset, pixelsY + paddingOffset, roomWidth + 2, roomHeight + 2);
    }
    public void drawGrid(){
        gridLayer.setStroke(Color.DARKGRAY);
        gridLayer.setGlobalAlpha(0.45);
        gridLayer.setLineWidth(2);

        int totalXLines = params.getGridX() - 2;
        int totalYLines = params.getGridY() - 2;
        int currentCanvasX = cellSize;
        int currentCanvasY = cellSize;
        for(int i = 0; i <= totalXLines; i++){
            gridLayer.strokeLine(currentCanvasX, 0, currentCanvasX,totalCanvasY);
            currentCanvasX += cellSize;
        }
        for(int i = 0; i <= totalYLines; i++){
            gridLayer.strokeLine(0, currentCanvasY, totalCanvasX, currentCanvasY);
            currentCanvasY += cellSize;
        }
    }
    public void clearGrid(){
        gridLayer.clearRect(0,0, totalCanvasX, totalCanvasY);
    }
    public void clearLinks(){
        linksLayer.clearRect(0,0, totalCanvasX, totalCanvasY);
    }
    public void drawLinks(){
        linksLayer.setFill(Color.BLACK);
        linksLayer.setStroke(Color.BLACK);
        linksLayer.setLineWidth(2);
        ArrayList<Link> allLinks = map.getCurrentLinks();
        for(Link i : allLinks){
            int direction1 = i.getRoomOneDirection();
            int X1 = i.getRoomOneX();
            int Y1 = i.getRoomOneY();
            int direction2 = i.getRoomTwoDirection();
            int X2 = i.getRoomTwoX();
            int Y2 = i.getRoomTwoY();
            int lineX1;
            int lineY1;
            int lineX2;
            int lineY2;
            if(direction1==1){
                lineX1 = params.get_NW_ExitX(X1);
                lineY1 = params.get_NW_ExitY(Y1);
            }
            else if(direction1==2){
                lineX1 = params.get_N_ExitX(X1);
                lineY1 = params.get_N_ExitY(Y1);
            }
            else if(direction1==3){
                lineX1 = params.get_NE_ExitX(X1);
                lineY1 = params.get_NE_ExitY(Y1);
            }
            else if (direction1==4){
                lineX1 = params.get_E_ExitX(X1);
                lineY1 = params.get_E_ExitY(Y1);
            }
            else if (direction1==5){
                lineX1 = params.get_SE_ExitX(X1);
                lineY1 = params.get_SE_ExitY(Y1);
            }
            else if (direction1==6){
                lineX1 = params.get_S_ExitX(X1);
                lineY1 = params.get_S_ExitY(Y1);
            }
            else if (direction1==7){
                lineX1 = params.get_SW_ExitX(X1);
                lineY1 = params.get_SW_ExitY(Y1);
            }
            else if (direction1==8){
                lineX1 = params.get_W_ExitX(X1);
                lineY1 = params.get_W_ExitY(Y1);
            }
            else if (direction1==9){
                lineX1 = params.get_U_ExitX(X1);
                lineY1 = params.get_U_ExitY(Y1);
            }
            else {
                lineX1 = params.get_D_ExitX(X1);
                lineY1 = params.get_D_ExitY(Y1);
            }

            if(direction2==1){
                lineX2 = params.get_NW_ExitX(X2);
                lineY2 = params.get_NW_ExitY(Y2);
            }
            else if(direction2==2){
                lineX2 = params.get_N_ExitX(X2);
                lineY2 = params.get_N_ExitY(Y2);
            }
            else if(direction2==3){
                lineX2 = params.get_NE_ExitX(X2);
                lineY2 = params.get_NE_ExitY(Y2);
            }
            else if (direction2==4){
                lineX2 = params.get_E_ExitX(X2);
                lineY2 = params.get_E_ExitY(Y2);
            }
            else if (direction2==5){
                lineX2 = params.get_SE_ExitX(X2);
                lineY2 = params.get_SE_ExitY(Y2);
            }
            else if (direction2==6){
                lineX2 = params.get_S_ExitX(X2);
                lineY2 = params.get_S_ExitY(Y2);
            }
            else if (direction2==7){
                lineX2 = params.get_SW_ExitX(X2);
                lineY2 = params.get_SW_ExitY(Y2);
            }
            else if (direction2==8){
                lineX2 = params.get_W_ExitX(X2);
                lineY2 = params.get_W_ExitY(Y2);
            }
            else if (direction2==9){
                lineX2 = params.get_U_ExitX(X2);
                lineY2 = params.get_U_ExitY(Y2);
            }
            else {
                lineX2 = params.get_D_ExitX(X2);
                lineY2 = params.get_D_ExitY(Y2);
            }
            int offsetToCenterLine = params.getExitMarkerOffset();
            linksLayer.strokeLine(lineX1 + offsetToCenterLine, lineY1 + offsetToCenterLine, lineX2 + offsetToCenterLine, lineY2 + offsetToCenterLine);

        }

    }

}

