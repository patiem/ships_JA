package gui.playing;

import com.fasterxml.jackson.databind.ObjectMapper;
import connection.Client;
import connection.MessageProcessor;
import gui.events.SunkShipEvent;
import gui.events.UpdateWhenHitEvent;
import gui.events.UpdateWhenMissedEvent;
import gui.events.YouHitEvent;
import gui.events.YouLostEvent;
import gui.events.YouMissedEvent;
import gui.events.YouWinEvent;
import gui.events.YourTurnEvent;
import gui.fields.Field;
import gui.fields.FieldSize;
import gui.fields.Mast;
import gui.fields.SeaField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import json.JsonParserAdapter;
import model.Position;
import model.Sea;
import model.ShipBoundariesPositions;
import responses.Response;
import responses.ResponseHeader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * It initializes and populates the playboard.
 *
 * @version 1.5
 */
public class PlayBoardController implements Initializable {
  private static final Logger LOGGER = Logger.getLogger(PlayBoardController.class.getName());

  private final Client client;
  private static final String POSITIONS_SEPARATOR = ",";
  private MessageProcessor processor;

  private List<Position> positions;
  private SeaField lastField;
  private Sea sea;
  private static final String SERVER_CONFIG_FILE = "config.properties";
  private static String LANGUAGE_CONFIG;
  private Properties properties;

  @FXML
  private GridPane shipBoard;
  @FXML
  private GridPane targetBoard;
  @FXML
  private TextField winning;

  public PlayBoardController(Client client, List<Position> positions) {
    this.client = client;
    this.positions = positions;
    setLanguage();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateSeaWithSeaFields();
    shipBoard.setDisable(true);
    winning.setText(properties.getProperty("wait"));
    populateOpponentBoardWithFleet();

    winning.addEventHandler(UpdateWhenHitEvent.UPDATE, updateBoardWhenHit);
    winning.addEventHandler(UpdateWhenMissedEvent.UPDATE_MISSED, updateBoardWhenMissed);
    winning.addEventHandler(YourTurnEvent.TURN, enableBoard);
    winning.addEventHandler(YouMissedEvent.WAIT, youMissed);
    winning.addEventHandler(YouWinEvent.WIN, youWin);
    winning.addEventHandler(YouLostEvent.LOST, youLost);
    winning.addEventHandler(YouHitEvent.HIT, youHit);
    winning.addEventHandler(SunkShipEvent.SUNK, shipSunk);

    makeMessageListenerThread();
  }

  private void setLanguage()  {
    properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(SERVER_CONFIG_FILE);
    try {
      properties.load(config);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String languageVersion = properties.getProperty("languageVersion");
    if(languageVersion.equals("Polish")) {
      LANGUAGE_CONFIG = "Polish.properties";
    }else {
      LANGUAGE_CONFIG = "English.properties";
    }
    InputStream language = ClassLoader.getSystemResourceAsStream(LANGUAGE_CONFIG);
    try {
      properties.load(language);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void makeMessageListenerThread() {
    new Thread(() -> {
      boolean isGameRunning = true;
      while (isGameRunning) {
        String message = client.getMessage();
        JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();
        try {
          Response responseToProcess = jsonParserAdapter.parse(message, Response.class, new ObjectMapper());
          processor.processMessage(responseToProcess);
          ResponseHeader header = responseToProcess.getHeader();

          if (header == ResponseHeader.WIN || header == ResponseHeader.LOST) {
            isGameRunning = false;
          }
        } catch (IOException e) {
          LOGGER.log(Level.SEVERE, e.getMessage());
        }
      }
    }).start();
  }

  private void populateSeaWithSeaFields() {
    sea = new Sea();
    int boardSize = 10;

    for (int i = 0; i < boardSize; i++) {
      for (int n = 0; n < boardSize; n++) {
        SeaField field = new SeaField(i, n, FieldSize.BIG);
        field.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          client.sendMessage(field);
          field.marked();
          lastField = field;
        });

        sea.addSeaField(field);
        shipBoard.getChildren().add(field);
        GridPane.setConstraints(field, i, n);
      }
    }
  }

  private void populateOpponentBoardWithFleet() {
    for (Position position : positions) {
      Mast smallMast = new Mast(position.getRow(), position.getColumn(), FieldSize.SMALL);
      targetBoard.getChildren().add(smallMast);
      GridPane.setConstraints(smallMast, position.getColumn(), position.getRow());
    }
  }

  private void putFieldOnOpponentBoard(Field field) {
    Platform.runLater(() -> {
      Node fieldAsNode = (Node) field;
      field.markAsHit();
      targetBoard.getChildren().add(fieldAsNode);
      GridPane.setConstraints(fieldAsNode, field.getRow(), field.getColumn());
    });
  }

  private final EventHandler<UpdateWhenHitEvent> updateBoardWhenHit =
      event -> {
        Integer index = Integer.valueOf(event.getMessage());
        Position fieldPosition = new Position(index);
        Mast hitMast = new Mast(fieldPosition.getRow(), fieldPosition.getColumn(), FieldSize.SMALL);
        putFieldOnOpponentBoard(hitMast);

      };

  private final EventHandler<UpdateWhenMissedEvent> updateBoardWhenMissed =
      event -> {
        Integer index = Integer.valueOf(event.getMessage());
        Position fieldPosition = new Position(index);
        SeaField seaHit = new SeaField(fieldPosition.getRow(), fieldPosition.getColumn(), FieldSize.SMALL);
        putFieldOnOpponentBoard(seaHit);
      };

  private final EventHandler<YourTurnEvent> enableBoard =
      event -> {
        winning.setText(properties.getProperty("play"));
        shipBoard.setDisable(false);
      };

  private final EventHandler<YouMissedEvent> youMissed =
      event -> {
        lastField.missed();
        winning.setText(properties.getProperty("wait"));
        shipBoard.setDisable(true);
      };

  private final EventHandler<YouWinEvent> youWin =
      event -> {
        lastField.hit();
        winning.setText(properties.getProperty("win"));
      };

  private final EventHandler<YouLostEvent> youLost =
      event -> winning.setText(properties.getProperty("loss"));

  private final EventHandler<YouHitEvent> youHit =
      event -> lastField.hit();

  private final EventHandler<SunkShipEvent> shipSunk =
      event -> {
        String[] positionsAsString = event.getMessage().split(POSITIONS_SEPARATOR);
        List<Integer> sunkShipPositions = Arrays.stream(positionsAsString).map(Integer::parseInt).collect(Collectors.toList());

        ShipBoundariesPositions shipBoundariesPositions = new ShipBoundariesPositions(sea);
        shipBoundariesPositions
            .calculateShipBoundariesPositions(sunkShipPositions)
            .markSunkShip();
      };

  public void setMessageProcessor(MessageProcessor messageProcessor) {
    this.processor = messageProcessor;
  }
}

