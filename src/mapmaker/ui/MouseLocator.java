package mapmaker.ui;


public class MouseLocator {
    public static int getMouseGridX(double mouseX, int cellSize){
        int XPos = (int) Math.floor((double) mouseX/cellSize);
        return XPos;
    }

    public static int getMouseGridY(double mouseY, int cellSize){
        int YPos = (int) Math.floor((double) mouseY/cellSize);
        return YPos;
    }
    public static int getExitLinkSelected(double mouseX, double mouseY, MapViewParams params){
        int markerSize = params.getExitMarkerSize();
        int cellDimensions = params.getCellDimensions();
        int gridX = getMouseGridX(mouseX, cellDimensions);
        int gridY = getMouseGridY(mouseY, cellDimensions);
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

}
