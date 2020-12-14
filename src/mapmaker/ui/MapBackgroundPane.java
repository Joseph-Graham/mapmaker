package mapmaker.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MapBackgroundPane {

    private Pane mapBackground;
    private MapViewParams mapParams;
    private MapViewPane mapViewPane;

    public MapBackgroundPane(MapViewParams mapParams, MapViewPane mapViewPane){
        this.mapViewPane = mapViewPane;
        this.mapParams = mapParams;
        this.mapBackground = new Pane();
        buildMapBackgroundPane();
    }
    public void buildMapBackgroundPane(){
        mapBackground.relocate(0, 50);
        mapBackground.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        mapBackground.setMinSize(mapParams.getTotalMapViewX() + mapParams.getCellDimensions(), mapParams.getTotalMapViewY() + mapParams.getCellDimensions());

        mapBackground.getChildren().add(mapViewPane.getMapViewPane());
    }
    public Pane getMapBackgroundPane(){
        return mapBackground;
    }

}
