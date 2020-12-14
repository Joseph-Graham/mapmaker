package mapmaker.ui;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class DataPane {

    private Pane dataPane;
    private MapViewParams mapParams;
    private MapView mapView;
    private DataPaneDisplay dataPaneDisplay;
    private DataPaneDisplayController controller;

    public DataPane(MapViewParams mapParams, MapView mapView){
        this.dataPane = new Pane();
        this.mapParams = mapParams;
        this.mapView = mapView;
        this.dataPaneDisplay = new DataPaneDisplay(dataPane);
        buildDataPane();
        this.controller = new DataPaneDisplayController(dataPane, mapView, dataPaneDisplay);

    }
    public void buildDataPane(){
        dataPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
        dataPane.setMinWidth(280);
        dataPane.setMinHeight(mapParams.getTotalMapViewY() + mapParams.getCellDimensions());
        dataPane.relocate(mapParams.getTotalMapViewX() + mapParams.getCellDimensions() + 10, 40);
    }


    public Pane getDataPane(){
        return dataPane;
    }
}
