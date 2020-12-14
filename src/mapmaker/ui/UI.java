package mapmaker.ui;

import com.sun.glass.ui.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mapmaker.obj.Grid;
import mapmaker.obj.Map;

import java.util.ArrayList;

public class UI {

    private ArrayList<Canvas> mapViewCanvases;

    private MapViewParams mapParams;
    private Grid grid;
    private MapViewPane mapViewPane;
    private MapBackgroundPane mapBackgroundPane;
    private TopButtonPane topButtonPane;
    private DataPane dataPane;
    private Pane basePane;
    private Map map;
    private Scene scene;
    private Stage primaryStage;

    public UI(Stage primaryStage){ //standard constructor for app startup; instantiates a default map with a default grid of 10x10
        this.primaryStage = primaryStage;
        this.map = new Map();
        this.grid = map.getGrid();
        this.mapParams = new MapViewParams(grid);
        this.topButtonPane = new TopButtonPane(mapParams, primaryStage, map);
        this.mapViewPane = new MapViewPane(mapParams, map);
        this.dataPane = new DataPane(mapParams, mapViewPane.getMapView());
        this.mapBackgroundPane = new MapBackgroundPane(mapParams, mapViewPane);
        this.basePane = new Pane();
        buildPanes();
        this.scene = new Scene(getBasePane());
        primaryStage.setTitle(map.getMapName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public UI(Stage primaryStage, Map map){ //constructor for loading a new map, which takes a map object containing the map data to be loaded
        this.primaryStage = primaryStage;
        this.map = map;
        this.grid = map.getGrid();
        this.mapParams = new MapViewParams(grid);
        this.topButtonPane = new TopButtonPane(mapParams, primaryStage, map);
        this.mapViewPane = new MapViewPane(mapParams, map);
        this.dataPane = new DataPane(mapParams, mapViewPane.getMapView());
        this.mapBackgroundPane = new MapBackgroundPane(mapParams, mapViewPane);
        this.basePane = new Pane();
        buildPanes();
        this.scene = new Scene(getBasePane());
        refreshMapView();
        primaryStage.setTitle(map.getMapName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void buildPanes() {
        basePane.getChildren().add(mapBackgroundPane.getMapBackgroundPane());
        basePane.getChildren().add(topButtonPane.getTopButtonPane());
        basePane.getChildren().add(dataPane.getDataPane());
        basePane.getChildren().add(mapViewPane.getMapViewPane());

        basePane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        basePane.setMinHeight(mapBackgroundPane.getMapBackgroundPane().getMinHeight() + 50);
        basePane.setMinWidth(mapParams.getTotalMapViewX() + mapParams.getCellDimensions() + 300);

    }
    public Pane getBasePane(){
        return basePane;
    }
    private void refreshMapView(){
        mapViewPane.refreshMapView();
    }
}
