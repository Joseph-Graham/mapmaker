package mapmaker.ui;

import mapmaker.obj.*;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mapmaker.util.SaveMap;
import org.w3c.dom.css.CSSStyleSheet;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;


public class MapMaker extends Application {

    @Override
    public void start(Stage primaryStage) {

        UI mapmakerUI = new UI(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}