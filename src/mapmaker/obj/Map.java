package mapmaker.obj;

import java.util.ArrayList;

public class Map {

    //public Room[ ] CurrentRooms;
    public ArrayList<Room> CurrentRooms;
    public ArrayList<Link> CurrentLinks;
    public Grid grid;

    public Map(Grid grid, ArrayList<Room> rooms){
        this.grid = grid;
        this.CurrentRooms = rooms;
    }
    public ArrayList<Room> getCurrentRooms(){
        return CurrentRooms;
    }
    public ArrayList<Link> getCurrentLinks(){
        return CurrentLinks;
    }
    public void addRoom(Room newRoom){
        CurrentRooms.add(newRoom);
    }
    public void deleteRoom(int x, int y){
        for(int i = 0; i < CurrentRooms.size(); i++){
            if((CurrentRooms.get(i).getX() == x) && (CurrentRooms.get(i).getY() == y)){
                CurrentRooms.remove(i);
            }
        }
    }
    /*public Room getRoomAt(int x, int y) {
        for (int i = 0; i < CurrentRooms.size(); i++) {
            if ((CurrentRooms.get(i).getX() == x) && (CurrentRooms.get(i).getY() == y)) {
                return CurrentRooms.get(i);
            }
        }
    }*/

    public void addLink(Room roomOne, Room roomTwo, int directionOne, int directionTwo){

    }


}
