package mapmaker.obj;

import mapmaker.util.CheckMapData;

import java.util.ArrayList;
import java.util.Collections;

public class Map {
    //object for holding all working room and link data, as well as grid object
    private String mapName;
    private ArrayList<Room> CurrentRooms;
    private ArrayList<Link> CurrentLinks;
    private Grid grid;

    public Map(Grid grid){
        this.mapName = "New Map";
        this.grid = grid;
        this.CurrentRooms = new ArrayList<>();
        this.CurrentLinks = new ArrayList<>();
    }
    public Map(Grid grid, String name){
        this.mapName = name;
        this.grid = grid;
        this.CurrentRooms = new ArrayList<>();
        this.CurrentLinks = new ArrayList<>();
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

        deleteAllLinksForRoom(x, y);

        for(int i = 0; i < CurrentRooms.size(); i++) {
            if ((CurrentRooms.get(i).getX() == x) && (CurrentRooms.get(i).getY() == y)) {
                CurrentRooms.remove(i);
            }
        }
    }
    public String getMapName(){
        return mapName;
    }
    /*public Room getRoomAt(int x, int y) {
        for (int i = 0; i < CurrentRooms.size(); i++) {
            if ((CurrentRooms.get(i).getX() == x) && (CurrentRooms.get(i).getY() == y)) {
                return CurrentRooms.get(i);
            }
        }
    }*/

    public void addLink(Link newLink){
        CurrentLinks.add(newLink);
        System.out.println("Added new link:");
        System.out.println("Room one at: " + newLink.getRoomOneX() + "," + newLink.getRoomOneY());
        System.out.println("Linked at: " + newLink.getRoomOneDirection());
        System.out.println("Room two at: " + newLink.getRoomTwoX() + "," + newLink.getRoomTwoY());
        System.out.println("Linked at: " + newLink.getRoomTwoDirection());
    }
    public void deleteSpecificLink(int x, int y, int direction){
        System.out.println(CurrentLinks.size() + " links currently on map");
        for(Link i : CurrentLinks){
            if((x == i.getRoomOneX() && y == i.getRoomOneY() && direction == i.getRoomOneDirection())
                || (x == i.getRoomTwoX() && y == i.getRoomTwoY() && direction == i.getRoomTwoDirection())){
                CurrentLinks.remove(i);
            }
        }
    }
    public void deleteAllLinksForRoom(int x, int y){
        System.out.println(CurrentLinks.size() + " links currently on map");
        ArrayList<Link> linksToRemove = new ArrayList<>();
        for(Link i : CurrentLinks){
            if((x == i.getRoomOneX() && y == i.getRoomOneY())||(x == i.getRoomTwoX() && y == i.getRoomTwoY())){
                linksToRemove.add(i);
            }
        }
        CurrentLinks.removeAll(linksToRemove);
    }

}
