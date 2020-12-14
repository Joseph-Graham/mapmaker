package mapmaker.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;

public class DataPaneDisplay {

    private Pane dataPane;
    private TextField attributeField1;
    private TextField attributeField2;
    private TextField attributeField3;
    private TextField attributeField4;
    private TextField attributeField5;
    private TextField flag;
    private TextArea description;
    private Button updateRoomFields;

    public DataPaneDisplay(Pane dataPane){
        this.dataPane = dataPane;
        this.attributeField1 = new TextField();
        this.attributeField2 = new TextField();
        this.attributeField3 = new TextField();
        this.attributeField4 = new TextField();
        this.attributeField5 = new TextField();
        this.flag = new TextField();
        this.description = new TextArea();
        this.updateRoomFields = new Button("Update");
        buildAttributeFields();
        buildDescriptionField();
        buildFlagFields();
        buildUpdateRoomFieldsButton();
    }
    private void buildAttributeFields(){
        dataPane.getChildren().add(attributeField1);
        attributeField1.relocate(50, 300);
        attributeField1.setFocusTraversable(false);

        dataPane.getChildren().add(attributeField2);
        attributeField2.relocate(50, 350);
        attributeField2.setFocusTraversable(false);

        dataPane.getChildren().add(attributeField3);
        attributeField3.relocate(50, 400);
        attributeField3.setFocusTraversable(false);

        dataPane.getChildren().add(attributeField4);
        attributeField4.relocate(50, 450);
        attributeField4.setFocusTraversable(false);

        dataPane.getChildren().add(attributeField5);
        attributeField5.relocate(50, 500);
        attributeField5.setFocusTraversable(false);
    }
    private void buildDescriptionField(){
        dataPane.getChildren().add(description);
        description.relocate(50, 550);
        description.setMinHeight(300);
        description.setMaxWidth(185);
        description.setFocusTraversable(false);
        description.setWrapText(true);

    }
    private void buildFlagFields(){
        dataPane.getChildren().add(flag);
        flag.relocate(50, 243);
        flag.setPrefSize(80, 40);
        flag.setPadding(new Insets(2, 2, 2, 2));
        flag.setFocusTraversable(false);
        Font flagFont = Font.font("Consolas", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 22);
        flag.setFont(flagFont);
    }
    private void buildUpdateRoomFieldsButton(){
        dataPane.getChildren().add(updateRoomFields);
        updateRoomFields.setPrefSize(85, 30);
        updateRoomFields.relocate(150, 248);
    }
    public TextField getAttributeField1(){
        return attributeField1;
    }
    public TextField getAttributeField2(){
        return attributeField2;
    }
    public TextField getAttributeField3(){
        return attributeField3;
    }
    public TextField getAttributeField4(){
        return attributeField4;
    }
    public TextField getAttributeField5(){
        return attributeField5;
    }
    public TextField getFlagField(){
        return flag;
    }
    public TextArea getDescriptionField(){
        return description;
    }
    public void setAttributeFieldText(ArrayList<String> attributes){
        if(attributes.size() >= 1){
            attributeField1.setText(attributes.get(0));
        }
        if(attributes.size() >= 2) {
            attributeField2.setText(attributes.get(1));
        }
        if(attributes.size() >= 3) {
            attributeField3.setText(attributes.get(2));
        }
        if(attributes.size() >= 4) {
            attributeField4.setText(attributes.get(3));
        }
        if(attributes.size() == 5) {
            attributeField5.setText(attributes.get(4));
        }
    }
    public void setFlag(String flagText){
        flag.setText(flagText);
    }
    public void setDescription(String desc){
        description.setText(desc);
    }
    public void clearData(){
        attributeField1.clear();
        attributeField2.clear();
        attributeField3.clear();
        attributeField4.clear();
        attributeField5.clear();
        description.clear();
        flag.clear();
    }
    public Button getUpdateRoomFields(){
        return updateRoomFields;
    }
    public ArrayList<String> getAttributeFieldText(){
        ArrayList<String> attributeFields = new ArrayList<>();
        attributeFields.add(attributeField1.getText());
        attributeFields.add(attributeField2.getText());
        attributeFields.add(attributeField3.getText());
        attributeFields.add(attributeField4.getText());
        attributeFields.add(attributeField5.getText());
        return attributeFields;
    }
    public String getDescriptionFieldText(){
        return description.getText();
    }
    public String getFlagFieldText(){
        return flag.getText();
    }
}

