package mapmaker.ui;


import mapmaker.obj.Map;
import mapmaker.obj.Room;

public class MouseLocator {

    private Map map;
    private MapViewParams params;

    public MouseLocator(Map map, MapViewParams params){
        this.params = params;
        this.map = map;

    }
    public int getMouseGridX(double mouseX){
        int XPos = (int) Math.floor((double) mouseX/params.getCellDimensions());
        return XPos;
    }

    public int getMouseGridY(double mouseY){
        int YPos = (int) Math.floor((double) mouseY/params.getCellDimensions());
        return YPos;
    }
    public int getExitLinkSelected(double mouseX, double mouseY){
        int markerSize = params.getExitMarkerSize();
        int gridX = getMouseGridX(mouseX);
        int gridY = getMouseGridY(mouseY);
        if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_NW_ExitX(gridX)/markerSize))
            &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_NW_ExitY(gridY)/markerSize))){
            return 1;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_N_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_N_ExitY(gridY)/markerSize))){
            return 2;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_NE_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_NE_ExitY(gridY)/markerSize))){
            return 3;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_E_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_E_ExitY(gridY)/markerSize))){
            return 4;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_SE_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_SE_ExitY(gridY)/markerSize))){
            return 5;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_S_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_S_ExitY(gridY)/markerSize))){
            return 6;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_SW_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_SW_ExitY(gridY)/markerSize))){
            return 7;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_W_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_W_ExitY(gridY)/markerSize))){
            return 8;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_U_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_U_ExitY(gridY)/markerSize))){
            return 9;
        }
        else if ((int) Math.floor((double) mouseX/markerSize)==((int) Math.floor((double) params.get_D_ExitX(gridX)/markerSize))
                &&(int) Math.floor((double) mouseY/markerSize)==((int) Math.floor((double) params.get_D_ExitY(gridY)/markerSize))){
            return 10;
        }
        else {
            return 0;
        }
    }
    public boolean roomExistsAtMouseLocation(int x, int y) {
        for (Room i : map.getCurrentRooms()) {
            if ((x == i.getX()) && (y == i.getY())) {
                //System.out.println("Room exists at: " + x + "," + y + " " + i.getX() + "," + i.getY());
                return true;
            }
        }
        return false;
    }
}
