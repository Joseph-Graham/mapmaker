package mapmaker.ui;

import javafx.scene.paint.Color;
import mapmaker.obj.Grid;

public class MapViewParams {
    public int cellDimensions;
    private int roomHeight;
    private int roomWidth;
    private int totalMapViewX;
    private int totalMapViewY;
    public int paddingOffset;
    private int exitMarkerSize;
    private int exitMarkerOffset;
    private int exitMarkerSpacing;
    private int gridX;
    private int gridY;
    private boolean gridOn;
    private Color defaultRoomColor;

    public MapViewParams(Grid grid) {
        //holds the parameters by which objects are rendered on the map
        //The rest of the UI elements are contingent on the cell dimensions (the size in pixels of the squares of the grid) to allow for scaling
        //referenced by MouseLocator to determine if the user has clicked on an existing room
        //referenced by the MapViewRenderer to determine room size, total size of the map view, exit marker size
        this.cellDimensions = 80;
        this.roomHeight = cellDimensions / 2;
        this.roomWidth = cellDimensions / 2;
        this.gridX = grid.getUnitsX();
        this.gridY = grid.getUnitsY();
        this.totalMapViewX = gridX*cellDimensions;
        this.totalMapViewY = gridY*cellDimensions;
        this.paddingOffset = roomWidth/2;
        this.exitMarkerSize = cellDimensions / 10; //exit marker diameter in pixels
        this.exitMarkerOffset = exitMarkerSize / 2; //number of pixels to offset the exit marker so that its centered over the room line
        this.exitMarkerSpacing = (roomWidth / 2); //half room size spacing for N, S, E, W, U, and D exit markers
    }
    public boolean isGridOn(){
        return gridOn;
    }
    public int getTotalMapViewX(){
        return totalMapViewX;
    }
    public int getTotalMapViewY(){
        return totalMapViewY;
    }
    public int getCellDimensions(){
        return cellDimensions;
    }
    public int getPaddingOffset(){
        return paddingOffset;
    }
    public int getRoomHeight(){
        return roomHeight;
    }
    public int getRoomWidth(){
        return roomWidth;
    }
    public int getExitMarkerSize(){
        return exitMarkerSize;
    }
    public int getExitMarkerOffset(){
        return exitMarkerOffset;
    }
    public int getGridX(){
        return gridX;
    }
    public int getGridY(){
        return gridY;
    }
    public int get_NW_ExitX(int mouseGridX){ //each method returns the X or Y position of one of the ten exit markers to be drawn, given the grid location
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return (canvasX - exitMarkerOffset);
    }
    public int get_NW_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return (canvasY - exitMarkerOffset);
    }
    public int get_N_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX + exitMarkerSpacing - exitMarkerOffset;
    }
    public int get_N_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY - exitMarkerOffset;
    }
    public int get_NE_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX + roomWidth - exitMarkerOffset;
    }
    public int get_NE_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY - exitMarkerOffset;
    }
    public int get_E_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX + roomWidth - exitMarkerOffset;
    }
    public int get_E_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY + exitMarkerSpacing - exitMarkerOffset;
    }
    public int get_SE_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX + roomWidth - exitMarkerOffset;
    }
    public int get_SE_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY + roomWidth - exitMarkerOffset;
    }
    public int get_S_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX + exitMarkerSpacing - exitMarkerOffset;
    }
    public int get_S_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY + roomWidth - exitMarkerOffset;
    }
    public int get_SW_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX - exitMarkerOffset;
    }
    public int get_SW_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY + roomWidth - exitMarkerOffset;
    }
    public int get_W_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX - exitMarkerOffset;
    }
    public int get_W_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY + exitMarkerSpacing - exitMarkerOffset;
    }
    public int get_U_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX + exitMarkerSpacing + exitMarkerOffset/2;
    }
    public int get_U_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY + exitMarkerSpacing - exitMarkerOffset;
    }
    public int get_D_ExitX(int mouseGridX){
        int canvasX = (mouseGridX*cellDimensions) + paddingOffset;
        return canvasX + exitMarkerSpacing - (exitMarkerOffset*2 + exitMarkerOffset/2);
    }
    public int get_D_ExitY(int mouseGridY){
        int canvasY = (mouseGridY*cellDimensions) + paddingOffset;
        return canvasY + exitMarkerSpacing - exitMarkerOffset;
    }

}