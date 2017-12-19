package building;

import connection.Client;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FleetDropController implements Initializable {

    private static final int FIELD_SIZE = 30;
    private static final int GRID_SIZE = 10;

    @FXML
    public GridPane shipBoard;

    @FXML
    public Pane port;

    @FXML
    public Rectangle ship4;

    @FXML
    public Rectangle ship3;

    @FXML
    public Rectangle ship3a;

    @FXML
    public Rectangle ship2;

    @FXML
    public Rectangle ship2a;

    @FXML
    public Rectangle ship2b;

    @FXML
    public Rectangle ship1;

    @FXML
    public Rectangle ship1a;

    @FXML
    public Rectangle ship1b;

    @FXML
    public Rectangle ship1c;

    private Rectangle buildShip;
    private Fleet fleet;
    private final Sea sea = new Sea();
    private final Client client;


    public FleetDropController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int column = 0; column < GRID_SIZE; column++) {
            for (int row = 0; row < GRID_SIZE; row++) {
                SeaField field = new SeaField(column, row);

                field.setOnDragEntered(changeColorwhenDragEntered);
                field.setOnDragExited(resetColorWhenDragExited);
                field.setOnDragOver(seaFieldAcceptEventDraggedOver);
                field.setOnDragDropped(seaFieldToShipWhenDraggedDone);
                field.isMarkedAsMastProperty().addListener(mastIsCreated);
                field.isMarkedAsBoundProperty().addListener(boundIsSetOnSea);

                shipBoard.getChildren().add(field);
                GridPane.setConstraints(field, column, row);
                sea.addSeaField(field);
            }
        }

        fleet = new Fleet(sea);
        List<Rectangle> ships = Arrays.asList(ship4, ship3, ship3a, ship2, ship2a, ship2b, ship1, ship1a, ship1b, ship1c);

        for (Rectangle theShip : ships) {
            theShip.addEventHandler(MouseEvent.MOUSE_ENTERED, makeShadowWhenMoveOver);
            theShip.addEventHandler(MouseEvent.DRAG_DETECTED, dragDetected);
            theShip.addEventHandler(MouseEvent.MOUSE_EXITED, event -> theShip.setEffect(null));
            theShip.setOnDragDone(event -> {
                theShip.setFill(Color.BLACK);
                event.consume();
            });
        }
    }

    EventHandler<DragEvent> seaFieldToShipWhenDraggedDone =
            new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    SeaField field = (SeaField) event.getSource();
                    field.marked();
                    Integer column = field.getColumn();
                    Integer row = field.getRow();

                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasString()) {
                        success = true;
                    }
                    int shipLength = (int) ((Rectangle) event.getGestureSource()).getHeight() / FIELD_SIZE;
                    Mast mast = new Mast(column, row);

                    shipBoard.getChildren().add(mast);
                    GridPane.setConstraints(mast, column, row);

                    buildShip.removeEventHandler(MouseEvent.DRAG_DETECTED, dragDetected);
                    buildShip.removeEventHandler(MouseEvent.MOUSE_ENTERED, makeShadowWhenMoveOver);
                    fleet.startToBuildOneShip(mast, shipLength);

                    event.setDropCompleted(success);
                    event.consume();
                    buildShip.setOpacity(0.2);
                    if (buildShip.getHeight() / FIELD_SIZE > 1) port.setDisable(true);
                }
            };

    EventHandler<DragEvent> seaFieldAcceptEventDraggedOver =
            event -> {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
                event.consume();
            };

    EventHandler<DragEvent> changeColorwhenDragEntered =
            event -> {
                SeaField field = (SeaField) event.getSource();
                field.setFill(Color.RED);
                event.consume();
            };

    EventHandler<DragEvent> resetColorWhenDragExited =
            event -> {
                SeaField field = (SeaField) event.getSource();
                field.reset();
                event.consume();
            };

    EventHandler<MouseEvent> dragDetected =
            event -> {
                buildShip = (Rectangle) event.getSource();
                Dragboard db = buildShip.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString("Ship");
                db.setContent(content);
                buildShip.setFill(Color.DARKBLUE);
                event.consume();
            };

    EventHandler<MouseEvent> makeShadowWhenMoveOver =
            (MouseEvent e) -> {
                DropShadow shadow = new DropShadow();
                ((Rectangle) e.getSource()).setEffect(shadow);
            };

    ChangeListener<Boolean> mastIsCreated =
            new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    port.setDisable(true);
                    SeaField field = (SeaField) ((BooleanProperty) observable).getBean();
                    Integer column = field.getColumn();
                    Integer row = field.getRow();
                    Mast mast = new Mast(column, row);
                    shipBoard.getChildren().add(mast);
                    GridPane.setConstraints(mast, column, row);
                    fleet.addNextMastToShip(mast);
                }
            };

    ChangeListener<Boolean> boundIsSetOnSea =
            new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    SeaField field = (SeaField) ((BooleanProperty) observable).getBean();
                    Integer column = field.getColumn();
                    Integer row = field.getRow();
                    port.setDisable(false);
                    BoundField bound = new BoundField(column, row);
                    shipBoard.getChildren().add(bound);
                    GridPane.setConstraints(bound, column, row);
                }
            };
}
