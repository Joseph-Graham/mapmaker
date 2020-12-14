package mapmaker.ui;

import javafx.scene.layout.Pane;
import mapmaker.obj.Map;

public class MapViewPane {

    private Pane mapViewPane;
    private MapViewParams mapParams;
    private MapView mapView;
    private Map map;
    private MapViewController mapControl;

    public MapViewPane(MapViewParams params, Map map){
        this.map = map;
        this.mapParams = params;
        this.mapViewPane = new Pane();
        this.mapView = new MapView(mapParams, map);
        this.mapControl = new MapViewController(mapView, mapParams, mapView.getCurrentState());
        buildMapViewPane();

    }
    public void buildMapViewPane(){
        mapViewPane.getChildren().add(mapView.getBackgroundColor());
        mapViewPane.getChildren().add(mapView.getGridCanvas());
        mapViewPane.getChildren().add(mapView.getLinkCanvas());
        mapViewPane.getChildren().add(mapView.getRoomCanvas());
        mapViewPane.getChildren().add(mapView.getFeedbackCanvas());
        mapViewPane.getChildren().add(mapView.getSpecialFeedbackCanvas());
        mapViewPane.getChildren().add(mapView.getExitMarkerCanvas());
        mapViewPane.getChildren().add(mapView.getControlCanvas());

        mapViewPane.relocate(mapParams.getCellDimensions()/2, mapParams.getCellDimensions()/2+50);
        mapViewPane.setMinSize(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY());
    }
    public MapView getMapView(){
        return mapView;
    }
    public Pane getMapViewPane(){
        return mapViewPane;
    }
    public void refreshMapView(){
        mapControl.refreshMapView();
    }
}
