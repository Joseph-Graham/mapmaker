package mapmaker.obj;

public class Link {
    public Room roomOne;
    public Room roomTwo;
    public int roomOneDirection;
    public int roomTwoDirection;
    public boolean roomOneExitIsOpen;
    public boolean roomTwoExitIsOpen;

    public Link(Room roomOne, Room roomTwo, int roomOneDirection, int roomTwoDirection){
        this.roomOne = roomOne;
        this.roomTwo = roomTwo;
        this.roomOneDirection = roomOneDirection;
        this.roomTwoDirection = roomTwoDirection;
        this.roomOneExitIsOpen = true;
        this.roomTwoExitIsOpen = true;
    }

    public Room getRoomOne(){
        return roomOne;
    }
    public Room getRoomTwo(){
        return roomTwo;
    }
    public int getRoomOneDirection(){
        return roomOneDirection;
    }
    public int getRoomTwoDirection(){
        return roomTwoDirection;
    }
    public boolean isRoomOneExitOpen(){
        return roomOneExitIsOpen;
    }
    public boolean isRoomTwoExitOpen(){
        return roomTwoExitIsOpen;
    }

}
