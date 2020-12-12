package mapmaker.obj;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int x, y;
    private String desc;
    private String flag;
    private ArrayList<String> attributes;
    private Color color;

    public Room(int x, int y){
        this.x = x;
        this.y = y;
        this.desc = "";
        this.flag = "#ROOM";
        this.attributes = new ArrayList<>();
        attributes.add("Fiery");
        attributes.add("Dark");
        attributes.add("Shitty");
        attributes.add("Scary");
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

    public String getFlag(){
        return flag;
    }

    public String getDesc() {
        return desc;
    }

    public String getAttributeAt(int index) {
        return attributes.get(index);
    }

    public ArrayList<String> getAttributes(){
        return attributes;
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
        this.attributes.set(index, attribute);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
