package mapmaker.obj;

public class Grid {
    //private final int GRID_SCALE = 10;
    private int unitsY, unitsX;

    public Grid(){
        unitsY = 10;
        unitsX = 10;
    }
    public Grid(int x, int y){
        this.unitsX = x;
        this.unitsY = y;
    }

    public int getUnitsY(){
        return unitsY;
    }
    public int getUnitsX(){
        return unitsX;
    }

    public void setUnitsY(int newLength) {
        this.unitsY = newLength;
    }
    public void setUnitsX(int newWidth){
        this.unitsX = newWidth;
    }

}
