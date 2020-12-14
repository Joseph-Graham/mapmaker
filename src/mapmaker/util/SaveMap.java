package mapmaker.util;

import javafx.scene.control.Alert;
import mapmaker.obj.Map;
import mapmaker.obj.Grid;
import mapmaker.obj.Room;
import mapmaker.obj.Link;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

public class SaveMap {
    public static void saveToJson(Map map, String path) throws FileNotFoundException {

        //String path = "C:\\personal\\mapmaker\\";
        //String path = "";
        Grid saveGrid = map.getGrid();
        JSONObject Map = new JSONObject();
        JSONObject GridData = new JSONObject();
        JSONArray Rooms = new JSONArray();
        JSONArray Links = new JSONArray();

        //Map.getOrDefault("Name", 1);
        //Map.getOrDefault("Grid", 2);
        //Map.getOrDefault("Rooms", 3);
        Map.put("Name", map.getMapName());
        Map.put("Grid", GridData);
        GridData.put("Width", saveGrid.getUnitsX());
        GridData.put("Height", saveGrid.getUnitsY());

        for(Room i : map.getCurrentRooms()){
            JSONObject room = new JSONObject();
            room.put("X Position", i.getX());
            room.put("Y Position", i.getY());
            room.put("Description", i.getDesc());
            room.put("Flag", i.getFlag());
            room.put("Color", i.getColor().toString());
            JSONArray attributes = new JSONArray();
            if(!i.getAttributes().isEmpty()) {
                for (String j : i.getAttributes()) {
                    attributes.add(j);
                }
            }
            room.put("Attributes", attributes);
            Rooms.add(room);
        }
        Map.put("Rooms", Rooms);

        for(Link i : map.getCurrentLinks()){
            JSONObject roomOne = new JSONObject();
            roomOne.put("X Position", i.getRoomOneX());
            roomOne.put("Y Position", i.getRoomOneY());
            roomOne.put("Direction", i.getRoomOneDirection());
            roomOne.put("Exit Blocked", i.isRoomOneExitOpen());
            JSONObject roomTwo = new JSONObject();
            roomTwo.put("X Position", i.getRoomTwoX());
            roomTwo.put("Y Position", i.getRoomTwoY());
            roomTwo.put("Direction", i.getRoomTwoDirection());
            roomTwo.put("Exit Blocked", i.isRoomTwoExitOpen());
            JSONObject link = new JSONObject();
            link.put("Room One", roomOne);
            link.put("Room Two", roomTwo);
            Links.add(link);
        }
        Map.put("Links", Links);

        //System.out.print(Map.toJSONString());

        String jsonAsString = Map.toString();

        File jsonOut = new File(path + map.getMapName() + ".json");
        PrintWriter jsonOutWriter = new PrintWriter(jsonOut);

        jsonOutWriter.printf("%s", jsonAsString);
        jsonOutWriter.close();
    }
    public static void throwSaveFileError(){
        Alert saveError = new Alert(Alert.AlertType.ERROR);
        saveError.setHeaderText("Error saving map");
        saveError.setContentText("There was some error in trying to save your map to the disk.");
    }
}
