package mapmaker.util;

import mapmaker.obj.Map;
import mapmaker.obj.Room;

public class CheckMapData {
    public static boolean roomExistsWhereClicked(int x, int y, Map map) {
        /*if (map.CurrentRooms.size() == 0) {
            return false;
        }
        else {*/
            for (Room i : map.CurrentRooms) {
                if ((x == i.getX()) && (y == i.getY())) {
                    System.out.println("Room exists at: " + x + "," + y + " " + i.getX() + "," + i.getY());
                    return true;
                    }
                }
            //}
        return false;
    }
}