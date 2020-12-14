package mapmaker.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mapmaker.obj.Map;
import mapmaker.util.LoadMap;
import mapmaker.util.SaveMap;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.AllPermission;

public class TopButtonPane {

    private Stage primaryStage;
    private Pane topButtonPane;
    private MapViewParams mapParams;
    private Map map;

    public TopButtonPane(MapViewParams params, Stage primaryStage, Map map){
        this.primaryStage = primaryStage;
        this.topButtonPane = new Pane();
        this.mapParams = params;
        this.map = map;
        buildTopButtonPane();
    }
    public void buildTopButtonPane() {

        topButtonPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        topButtonPane.setMinHeight(50);
        topButtonPane.setMinWidth(mapParams.getTotalMapViewX() + mapParams.getCellDimensions());
        topButtonPane.relocate(0, 0);

        MenuBar topMenuBar = new MenuBar();
        Menu topMenuBar_File = new Menu("File");
        MenuItem topMenuBar_File_New = new MenuItem("New");
        MenuItem topMenuBar_File_Load = new MenuItem("Load");
        MenuItem topMenuBar_File_Save = new MenuItem("Save");
        Menu topMenuBar_Edit = new Menu("Edit");
        MenuItem topMenuBar_Edit_Clear_links = new MenuItem("Clear links");
        MenuItem topMenuBar_Edit_Clear_rooms = new MenuItem("Clear rooms");
        Menu topMenuBar_View = new Menu("View");
        MenuItem topMenuBar_View_ColorScheme = new MenuItem("Color scheme");

        topMenuBar_File.getItems().addAll(topMenuBar_File_New, topMenuBar_File_Load, topMenuBar_File_Save);
        topMenuBar.getMenus().add(topMenuBar_File);

        topMenuBar_Edit.getItems().addAll(topMenuBar_Edit_Clear_links, topMenuBar_Edit_Clear_rooms);
        topMenuBar.getMenus().add(topMenuBar_Edit);

        topMenuBar_View.getItems().addAll(topMenuBar_View_ColorScheme);
        topMenuBar.getMenus().add(topMenuBar_View);

        topMenuBar.setMinWidth(mapParams.getTotalMapViewX() + mapParams.getCellDimensions() + 300);



        topButtonPane.getChildren().add(topMenuBar);

        topMenuBar_File_Save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser pickFile = new FileChooser();
                pickFile.setTitle("Save map file");
                File filePicked = pickFile.showSaveDialog(primaryStage);
                if(filePicked != null) {
                    try {
                        SaveMap.saveToJson(map, filePicked.getAbsolutePath());
                    } catch (FileNotFoundException e) {
                        SaveMap.throwSaveFileError();
                    }
                }
            }
        });
        topMenuBar_File_Load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser pickFile = new FileChooser();
                pickFile.setTitle("Open a map file");
                File filePicked = pickFile.showOpenDialog(primaryStage);
                if (filePicked != null) {
                    try {
                        LoadMap.LoadFromJson(filePicked.getAbsolutePath(), primaryStage);
                    } catch (FileNotFoundException e) {
                        LoadMap.throwFileLoadError();
                    } catch (ParseException p) {
                        LoadMap.throwJSONParseError();
                    }
                }

            }
        });
        topMenuBar_File_New.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //clear the screen for a new map
            }
        });

    }
    public Pane getTopButtonPane(){
        return topButtonPane;
    }
}
