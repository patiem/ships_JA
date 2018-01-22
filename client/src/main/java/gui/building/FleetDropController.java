package gui.building;

import connection.Client;
import connection.FleetSender;
import gui.OutputChannelDispatcher;
import gui.fields.BoundField;
import gui.fields.FieldSize;
import gui.fields.Mast;
import gui.fields.SeaField;
import gui.starting.ConnectEvent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import messages.LanguageVersion;
import model.Fleet;
import model.Player;
import model.Position;
import model.PossiblePositions;
import model.randomize.RandomFleetGenerator;
import model.Sea;
import model.SeaCleaner;
import model.Ship;
import model.ShipBoardUpdater;
import model.ShipBoundariesPositions;
import model.ShipCreator;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * It displays the board used for deploying each player's fleetCreator.
 *
 * @version 1.5
 */
public class FleetDropController implements Initializable {

  private static final int FIELD_SIZE = 30;
  private static final int GRID_SIZE = 10;
  private static final String EMPTY_FLEET_INFO = "You can't play with empty fleet";

  private Sea sea;
  private Fleet fleet;
  private final Client client;
  private LanguageVersion languageVersion = new LanguageVersion();
  private OutputChannelDispatcher outputChannelDispatcher = new  OutputChannelDispatcher();

  @FXML
  private Button connectButton;
  @FXML
  private GridPane shipBoard;
  @FXML
  private Pane port;
  @FXML
  private Rectangle ship4;
  @FXML
  private Rectangle ship3;
  @FXML
  private Rectangle ship3a;
  @FXML
  private Rectangle ship2;
  @FXML
  private Rectangle ship2a;
  @FXML
  private Rectangle ship2b;
  @FXML
  private Rectangle ship1;
  @FXML
  private Rectangle ship1a;
  @FXML
  private Rectangle ship1b;
  @FXML
  private Rectangle ship1c;
  @FXML
  private TextField userName;
  @FXML
  private TextField info;
  @FXML
  private Button nextButton;
  @FXML
  private Button randomFleet;

  private Rectangle buildShip;
  private ShipCreator shipCreator;
  private ShipBoardUpdater boardUpdater;

  /**
   * Creates FleetDropController instance.
   *
   * @param client - client instance
   */

  public FleetDropController(Client client, Sea sea, Fleet fleet) {
    this.client = client;
    this.sea = sea;
    this.fleet = fleet;
  }

  /**
   * It implements the 'initialize' method from the Initializable interface.
   *
   * @param location  - required to implement the method
   * @param resources - required to implement the method
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {

    populateSeaWithActiveFields();
    addEventHandlerToConnectButton();
    randomFleet.addEventHandler(MouseEvent.MOUSE_CLICKED, randomizeFleet);

    addEventHandlersToShips();
    setupShipBoardUpdater();
    this.connectButton.setText(languageVersion.getMessage("play"));
  }

  private void populateSeaWithActiveFields() {
    for (int column = 0; column < GRID_SIZE; column++) {
      for (int row = 0; row < GRID_SIZE; row++) {
        SeaField field = new SeaField(column, row, FieldSize.BIG);

        field.setOnDragEntered(changeColorWhenDragEntered);
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
  }

  private void addEventHandlerToConnectButton() {
    connectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, connectWhenClicked);
  }

  private void addEventHandlersToShips() {
    List<Rectangle> ships = Arrays.asList(ship4, ship3, ship3a, ship2, ship2a,
        ship2b, ship1, ship1a, ship1b, ship1c);

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

  private void setupShipBoardUpdater() {
    PossiblePositions possiblePositions = new PossiblePositions(sea);
    ShipBoundariesPositions boundariesPositions = new ShipBoundariesPositions(sea);
    SeaCleaner seaCleaner = new SeaCleaner(sea);
    boardUpdater = new ShipBoardUpdater(possiblePositions, boundariesPositions, seaCleaner);
  }

  private final EventHandler<MouseEvent> connectWhenClicked =
      event -> {
        if (fleet.isFleetFullyBuilt()) {
          setupClient();
          Player player = new Player(fleet, userName.getText());
          FleetSender fleetSender = new FleetSender(getClient(), player);
          fleetSender.sendFleetToServer();
          nextButton.fireEvent(new ConnectEvent());
        } else {
          info.setText(languageVersion.getMessage("emptyFleetInfo"));
          outputChannelDispatcher.printToDesiredOutput(languageVersion.getMessage("emptyFleetInfo"));
        }
        event.consume();
      };

  private final EventHandler<MouseEvent> randomizeFleet =
      event -> {
          RandomFleetGenerator randomFleetGenerator = new RandomFleetGenerator(sea);
          fleet = randomFleetGenerator.generateRandomFleet();

          fleet.getFields().forEach( x ->{
              shipBoard.getChildren().add((Mast) x) ;
                GridPane.setConstraints((Node) x, x.getColumn(), x.getRow());
              }
          );
        event.consume();
      };

  private final EventHandler<DragEvent> seaFieldAcceptEventDraggedOver =
      event -> {
        Dragboard db = event.getDragboard();
        if (db.hasString()) {
          event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
      };

  private final EventHandler<DragEvent> changeColorWhenDragEntered =
      event -> {
        SeaField field = (SeaField) event.getSource();
        field.setFill(Color.RED);
        event.consume();
      };

  private final EventHandler<DragEvent> resetColorWhenDragExited =
      event -> {
        SeaField field = (SeaField) event.getSource();
        field.resetColors();
        event.consume();
      };

  private final EventHandler<MouseEvent> makeShadowWhenMoveOver =
      event -> {
        DropShadow shadow = new DropShadow();
        ((Rectangle) event.getSource()).setEffect(shadow);
      };

  private final EventHandler<MouseEvent> dragDetected =
      event -> {
        buildShip = (Rectangle) event.getSource();
        Dragboard db = buildShip.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString("Ship");
        db.setContent(content);
        buildShip.setFill(Color.DARKBLUE);
        event.consume();
      };

  private final EventHandler<DragEvent> seaFieldToShipWhenDraggedDone =
      event -> {
        SeaField field = (SeaField) event.getSource();
        field.marked();
        Integer column = field.getColumn();
        Integer row = field.getRow();

        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
          success = true;
        }
        Mast mast = new Mast(column, row, FieldSize.BIG);
        shipBoard.getChildren().add(mast);
        GridPane.setConstraints(mast, column, row);

        buildShip.removeEventHandler(MouseEvent.DRAG_DETECTED, dragDetected);
        buildShip.removeEventHandler(MouseEvent.MOUSE_ENTERED, makeShadowWhenMoveOver);
        buildShip.setOpacity(0.2);
        if (buildShip.getHeight() / FIELD_SIZE > 1) {
          port.setDisable(true);
        }

        int shipLength = (int) ((Rectangle) event.getGestureSource()).getHeight() / FIELD_SIZE;

        createShipWhenDragDone(shipLength, mast);

        event.setDropCompleted(success);
        event.consume();

      };

  private void createShipWhenDragDone(int shipLength, Mast mast) {
    Ship ship = new Ship(shipLength);
    shipCreator = new ShipCreator(ship);
    shipCreator.addMastToShip(mast);
    boardUpdater.update(ship);
    fleet.addShip(ship);

  }

  private final ChangeListener<Boolean> mastIsCreated =
      new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable,
                            Boolean oldValue, Boolean newValue) {
          port.setDisable(true);
          SeaField field = (SeaField) ((BooleanProperty) observable).getBean();
          Integer column = field.getColumn();
          Integer row = field.getRow();
          Mast mast = new Mast(column, row, FieldSize.BIG);
          shipBoard.getChildren().add(mast);
          GridPane.setConstraints(mast, column, row);
          shipCreator.addMastToShip(mast);
          boardUpdater.update();
        }
      };

  private final ChangeListener<Boolean> boundIsSetOnSea =
      new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable,
                            Boolean oldValue, Boolean newValue) {
          SeaField field = (SeaField) ((BooleanProperty) observable).getBean();
          Integer column = field.getColumn();
          Integer row = field.getRow();
          port.setDisable(false);
          BoundField bound = new BoundField(column, row, FieldSize.BIG);
          shipBoard.getChildren().add(bound);
          GridPane.setConstraints(bound, column, row);
        }
      };

  public List<Position> listOfMasts() {
    return fleet.mastsPositions();
  }

  private Client getClient() {
    return client;
  }

  private void setupClient() {
    client.run();
  }
}
