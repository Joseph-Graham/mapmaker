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
}
