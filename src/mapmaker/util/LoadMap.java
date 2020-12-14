package mapmaker.util;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mapmaker.obj.Grid;
import mapmaker.obj.Link;
import mapmaker.obj.Map;
import mapmaker.obj.Room;
import mapmaker.ui.MapMaker;
import mapmaker.ui.UI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadMap {

    public static void LoadFromJson(String filepath, Stage primaryStage) throws FileNotFoundException, ParseException {

        File loadFile = new File(filepath);

        JSONParser loadJSON = new JSONParser();
        Scanner loadFileRead = new Scanner(loadFile);

        String mapAsString = "";

        while (loadFileRead.hasNext()){
            mapAsString = mapAsString + loadFileRead.nextLine().replaceAll("\n", "");
        }

        JSONObject mapAsJSON = (JSONObject) loadJSON.parse(mapAsString);
        System.out.println(mapAsString);
        JSONObject gridData = (JSONObject) mapAsJSON.get("Grid");

        String name = mapAsJSON.get("Name").toString();

        int gridX = Integer.parseInt(gridData.get("Width").toString());
        int gridY = Integer.parseInt(gridData.get("Height").toString());

        Grid loadGrid = new Grid(gridX, gridY);

        JSONArray rooms = (JSONArray) mapAsJSON.get("Rooms");
        ArrayList<Room> roomsToLoad = new ArrayList<>();

        for(int i = 0; i < rooms.size(); i++){
            JSONObject currentRoom = (JSONObject) rooms.get(i);
            int roomX = Integer.parseInt(currentRoom.get("X Position").toString());
            int roomY = Integer.parseInt(currentRoom.get("Y Position").toString());
            String desc = currentRoom.get("Description").toString();
            String flag = currentRoom.get("Flag").toString();
            //String colorAsString = currentRoom.get("Color").toString();
            Color roomColor = Color.valueOf(currentRoom.get("Color").toString());
            JSONArray attributesJSONArray = (JSONArray) currentRoom.get("Attributes");
            ArrayList<String> attributes = new ArrayList<>();
            for(int a = 0; a < attributesJSONArray.size(); a++){
                attributes.add(attributesJSONArray.get(a).toString());
            }
            Room loadRoom = new Room(roomX, roomY, attributes, desc, flag, roomColor);
            roomsToLoad.add(loadRoom);
        }

        JSONArray links = (JSONArray) mapAsJSON.get("Links");
        ArrayList<Link> linksToLoad = new ArrayList<>();

        for(int L = 0; L < links.size(); L++){
            JSONObject currentLink = (JSONObject) links.get(L);
            System.out.println(links.get(L));
            JSONObject roomOne = (JSONObject) currentLink.get("Room One");
            System.out.println(roomOne);
            int roomOneX = Integer.parseInt(roomOne.get("X Position").toString());
            int roomOneY = Integer.parseInt(roomOne.get("Y Position").toString());
            int direction1 = Integer.parseInt(roomOne.get("Direction").toString());
            boolean exitOneBlocked = Boolean.parseBoolean(roomOne.get("Exit Blocked").toString());

            JSONObject roomTwo = (JSONObject) currentLink.get("Room Two");
            int roomTwoX = Integer.parseInt(roomTwo.get("X Position").toString());
            int roomTwoY = Integer.parseInt(roomTwo.get("Y Position").toString());
            int direction2 = Integer.parseInt(roomTwo.get("Direction").toString());
            boolean exitTwoBlocked = Boolean.parseBoolean(roomTwo.get("Exit Blocked").toString());
            Link loadLink = new Link(roomOneX, roomOneY, roomTwoX, roomTwoY, direction1, direction2, exitOneBlocked, exitTwoBlocked);
            linksToLoad.add(loadLink);

        }
        Map mapToLoad = new Map(name, loadGrid, roomsToLoad, linksToLoad);

        primaryStage.hide();
        Stage newPrimaryStage = new Stage();
        UI refreshUI = new UI(newPrimaryStage, mapToLoad);

    }
    public static void throwFileLoadError(){
        Alert fileError = new Alert(Alert.AlertType.ERROR);
        fileError.setHeaderText("File load error.");
        fileError.setContentText("There was some error opening the specified save file");
        fileError.showAndWait();
    }
    public static void throwJSONParseError(){
        Alert jsonError = new Alert(Alert.AlertType.ERROR);
        jsonError.setHeaderText("Error in parsing JSON object.");
        jsonError.setContentText("Check the save file and try again");
        jsonError.showAndWait();
    }
}
