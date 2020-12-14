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
        this.color = Color.BLACK;
    }
    public Room(int x, int y, ArrayList<String> attributes, String description, String flag, Color color){
        this.x = x;
        this.y = y;
        this.attributes = attributes;
        this.desc = description;
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

    public void setAttributes(ArrayList<String> newAttributes) {
        attributes.clear();
        attributes.addAll(newAttributes);
    }
    public void setFlag(String newFlag){
        this.flag = newFlag;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
