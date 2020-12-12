package mapmaker.obj;

public class Link {
    //public Room roomOne;
    //public Room roomTwo;
    private int roomOneX;
    private int roomOneY;
    private int roomTwoX;
    private int roomTwoY;
    private int roomOneDirection;
    private int roomTwoDirection;
    private boolean roomOneExitIsOpen;
    private boolean roomTwoExitIsOpen;

    public Link(int roomOneX, int roomOneY, int roomTwoX, int roomTwoY, int roomOneDirection, int roomTwoDirection){
        this.roomOneX = roomOneX;
        this.roomOneY = roomOneY;
        this.roomTwoX = roomTwoX;
        this.roomTwoY = roomTwoY;
        this.roomOneDirection = roomOneDirection;
        this.roomTwoDirection = roomTwoDirection;
        this.roomOneExitIsOpen = true;
        this.roomTwoExitIsOpen = true;
    }

    public int getRoomOneDirection(){
        return roomOneDirection;
    }
    public int getRoomTwoDirection(){
        return roomTwoDirection;
    }
    public int getRoomOneX(){
        return roomOneX;
    }
    public int getRoomOneY(){
        return roomOneY;
    }
    public int getRoomTwoX(){
        return roomTwoX;
    }
    public int getRoomTwoY(){
        return roomTwoY;
    }
    public boolean isRoomOneExitOpen(){
        return roomOneExitIsOpen;
    }
    public boolean isRoomTwoExitOpen(){
        return roomTwoExitIsOpen;
    }

}
