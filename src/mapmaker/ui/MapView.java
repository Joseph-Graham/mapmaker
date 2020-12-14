package mapmaker.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import mapmaker.obj.Grid;
import mapmaker.obj.Map;
import mapmaker.obj.Room;

import java.util.ArrayList;

public class MapView {

    private Canvas BackgroundColor;
    private Canvas LinkCanvas;
    private Canvas RoomCanvas;
    private Canvas ExitMarkerCanvas;
    private Canvas FeedbackCanvas;
    private Canvas SpecialFeedbackCanvas;
    private Canvas GridCanvas;
    private Canvas ControlCanvas;
    private GraphicsContext background;
    private GraphicsContext linksLayer;
    private GraphicsContext roomsLayer;
    private GraphicsContext exitMarkerLayer;
    private GraphicsContext highlightLayer;
    private GraphicsContext specialHighlightLayer;
    private GraphicsContext gridLayer;
    private Grid grid;
    private MapViewParams mapParams;
    private MapViewState currentState;
    private Map map;

    public MapView(MapViewParams params, Map map){
        this.mapParams = params;
        this.currentState = new MapViewState();

        this.BackgroundColor = new Canvas(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY()); //layer to serve as the background color for the map layers
        this.LinkCanvas = new Canvas(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY());
        this.RoomCanvas = new Canvas(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY());
        this.ExitMarkerCanvas = new Canvas(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY());
        this.FeedbackCanvas = new Canvas(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY());
        this.SpecialFeedbackCanvas = new Canvas(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY());
        this.GridCanvas = new Canvas(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY());
        this.ControlCanvas = new Canvas(mapParams.getTotalMapViewX(), mapParams.getTotalMapViewY());

        this.background = BackgroundColor.getGraphicsContext2D();
        this.linksLayer = LinkCanvas.getGraphicsContext2D();
        this.roomsLayer = RoomCanvas.getGraphicsContext2D();
        this.exitMarkerLayer = ExitMarkerCanvas.getGraphicsContext2D();
        this.highlightLayer = FeedbackCanvas.getGraphicsContext2D();
        this.specialHighlightLayer = SpecialFeedbackCanvas.getGraphicsContext2D();
        this.gridLayer = GridCanvas.getGraphicsContext2D();
        this.grid = new Grid();
        this.map = map;

    }
    public MapViewState getCurrentState(){
        return currentState;
    }
    public GraphicsContext getBackground(){
        return background;
    }
    public GraphicsContext getLinksLayer(){
        return linksLayer;
    }
    public GraphicsContext getRoomsLayer(){
        return roomsLayer;
    }
    public GraphicsContext getExitMarkerLayer(){
        return exitMarkerLayer;
    }
    public GraphicsContext getHighlightLayer(){
        return highlightLayer;
    }
    public GraphicsContext getSpecialHighlightLayer(){
        return specialHighlightLayer;
    }
    public GraphicsContext getGridLayer(){
        return gridLayer;
    }
    public Grid getGrid(){
        return map.getGrid();
    }
    public Map getMap(){
        return map;
    }
    public Canvas getBackgroundColor(){
        return BackgroundColor;
    }
    public Canvas getLinkCanvas(){
        return LinkCanvas;
    }
    public Canvas getRoomCanvas(){
        return RoomCanvas;
    }
    public Canvas getExitMarkerCanvas(){
        return ExitMarkerCanvas;
    }
    public Canvas getFeedbackCanvas(){
        return FeedbackCanvas;
    }
    public Canvas getSpecialFeedbackCanvas(){
        return SpecialFeedbackCanvas;
    }
    public Canvas getGridCanvas(){
        return GridCanvas;
    }
    public Canvas getControlCanvas(){
        return ControlCanvas;
    }

}
