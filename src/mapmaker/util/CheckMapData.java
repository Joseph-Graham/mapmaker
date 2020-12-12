package mapmaker.util;

import mapmaker.obj.Map;
import mapmaker.obj.Room;

public class CheckMapData {
    public static boolean roomExistsAtMouseLocation(int x, int y, Map map) {
            for (Room i : map.getCurrentRooms()) {
                if ((x == i.getX()) && (y == i.getY())) {
                    //System.out.println("Room exists at: " + x + "," + y + " " + i.getX() + "," + i.getY());
                    return true;
                    }
                }
        return false;
    }
}