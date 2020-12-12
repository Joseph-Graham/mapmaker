package mapmaker.ui;

public class MapViewState {

    private boolean roomIsSelected;
    private boolean linkExitSelectionMode;
    private int selectedX;
    private int selectedY;
    private int linkRoomOneX;
    private int linkRoomOneY;
    private int linkRoomTwoX;
    private int linkRoomTwoY;
    private int linkRoomOneDirection;
    private int linkRoomTwoDirection;


    public MapViewState(){
        this.roomIsSelected = false;
        this.linkExitSelectionMode = false;
    }
    public boolean inSelectionMode(){
        return roomIsSelected;
    }
    public boolean inLinkExitSelectionMode(){
        return linkExitSelectionMode;
    }
    public boolean roomHoveredIsSelected(int x, int y){
        if(x == selectedX && y == selectedY){
            return true;
        }
        return false;
    }
    public void roomHasBeenSelected(int x, int y){
        this.roomIsSelected = true;
        this.selectedX = x;
        this.selectedY = y;
    }
    public int getSelectedX(){
        return selectedX;
    }
    public int getSelectedY(){
        return selectedY;
    }
    public void clearRoomSelections(){
        this.roomIsSelected = false;
    }
    public void firstlinkHasBeenSelected(int roomX, int roomY, int direction){
        this.linkRoomOneX = roomX;
        this.linkRoomOneY = roomY;
        this.linkRoomOneDirection = direction;
        this.linkExitSelectionMode = true;
        System.out.print("You selected an exit point on the selected room");
    }
    public void clearLinkSelection(){
        this.linkExitSelectionMode = false;
    }
    public void secondLinkHasBeenSelected(int roomX, int roomY, int direction){
        this.linkRoomTwoX = roomX;
        this.linkRoomTwoY = roomY;
        this.linkRoomTwoDirection = direction;
    }
    public int getLinkRoomOneX(){
        return linkRoomOneX;
    }
    public int getLinkRoomOneY(){
        return linkRoomOneY;
    }
    public int getLinkRoomTwoX(){
        return linkRoomTwoX;
    }
    public int getLinkRoomTwoY(){
        return linkRoomTwoY;
    }
    public int getLinkRoomOneDirection(){
        return linkRoomOneDirection;
    }
    public int getLinkRoomTwoDirection(){
        return linkRoomTwoDirection;
    }

}
