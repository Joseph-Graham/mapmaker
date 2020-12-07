package mapmaker.obj;

import javafx.scene.paint.Color;

public class Room {
    public int x, y;
    public String desc;
    public String flag;
    public String[] attributes;
    public Color color;
    public Room(int x, int y){
        this.x = x;
        this.y = y;
        this.flag = "#ROOM";
        this.color = Color.BLACK;
    }
    public Room(int x, int y, String flag, Color color){
        this.x = x;
        this.y = y;
        this.flag = flag;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDesc() {
        return desc;
    }

    public String getAttributeAt(int index) {
        return attributes[index];
    }

    public Color getColor() {
        return color;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public void setDesc(String newDesc) {
        this.desc = newDesc;
    }

    public void setAttributeAt(int index, String attribute) {
        this.attributes[index] = attribute;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
