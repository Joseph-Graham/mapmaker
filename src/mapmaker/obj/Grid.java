package mapmaker.obj;

public class Grid {
    public final int GRID_SCALE = 10;
    public int Length, Width;
    public int cellDimensions;

    public Grid(){
        Length = 10;
        Width = 20;
        cellDimensions = 100;
    }

    public Grid(int Length, int Width, int cellDimensions){
        this.Length = Length;
        this.Width = Width;
        this.cellDimensions = cellDimensions;
    }
    public int getLength(){
        return Length;
    }
    public int getWidth(){
        return Width;
    }
    public int getCellDimensions(){
        return cellDimensions;
    }
    public void setLength(int newLength) {
        this.Length = newLength;
    }
    public void setWidth(int newWidth){
        this.Width = newWidth;
    }
    public void setCellDimensions(int newCellDimensions){
        this.cellDimensions = newCellDimensions;
    }
}
