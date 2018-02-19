package gui.playing;

import com.fasterxml.jackson.databind.ObjectMapper;
import connection.Client;
import connection.MessageProcessor;
import dto.TranscriptDTO;
import file.FileWriter;
import gui.events.HitAgainEvent;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import json.JsonParserAdapter;
import messages.LanguageVersion;
import model.Position;
import model.Sea;
import model.ShipBoundariesPositions;
import responses.Response;
import responses.ResponseHeader;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  private LanguageVersion languageVersion = new LanguageVersion();
  private Map<String,String> messageMap = new HashMap<>();

  @FXML
  private GridPane shipBoard;
  @FXML
  private GridPane targetBoard;
  @FXML
  private TextField winning;
  @FXML
  private ImageView jack;
  @FXML
  private ImageView sunk;
  @FXML
  private Button transcriptBtn;

  public PlayBoardController(Client client, List<Position> positions) {
    this.client = client;
    this.positions = positions;
    populateMap();
  }

  private void populateMap() {
    messageMap.put("hitAgainMessage",languageVersion.getMessage("hit_again"));
    messageMap.put("waitMessage",languageVersion.getMessage("wait"));
    messageMap.put("playMessage",languageVersion.getMessage("play"));
    messageMap.put("winMessage",languageVersion.getMessage("win"));
    messageMap.put("lossMessage",languageVersion.getMessage("loss"));
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    populateSeaWithSeaFields();
    shipBoard.setDisable(true);
    winning.setText(messageMap.get("waitMessage"));
    populateOpponentBoardWithFleet();

    winning.addEventHandler(UpdateWhenHitEvent.UPDATE, updateBoardWhenHit);
    winning.addEventHandler(UpdateWhenMissedEvent.UPDATE_MISSED, updateBoardWhenMissed);
    winning.addEventHandler(YourTurnEvent.TURN, enableBoard);
    winning.addEventHandler(YouMissedEvent.WAIT, youMissed);
    winning.addEventHandler(YouWinEvent.WIN, youWin);
    winning.addEventHandler(YouLostEvent.LOST, youLost);
    winning.addEventHandler(YouHitEvent.HIT, youHit);
    winning.addEventHandler(SunkShipEvent.SUNK, shipSunk);
    winning.addEventHandler(HitAgainEvent.HIT_AGAIN, hitAgain);

    transcriptBtn.setText("Save transcript");
    addEventHandlerToTranscriptBtn();

    makeMessageListenerThread();
  }

  private void makeMessageListenerThread() {
    new Thread(() -> {
      boolean isGameRunning = true;
      while (isGameRunning) {
        String message = client.getMessage();
        JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();
        try {
          Response responseToProcess = jsonParserAdapter.parse(
              message, Response.class, new ObjectMapper());
          if(!responseToProcess.getTranscriptDTOs().isEmpty()){
              String transcript = responseToProcess.getTranscriptDTOs().stream().map(TranscriptDTO::toString).collect(Collectors.joining("\n"));
              FileWriter.write(transcript);
          }
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
        SeaField seaHit = new SeaField(
            fieldPosition.getRow(), fieldPosition.getColumn(), FieldSize.SMALL);
        putFieldOnOpponentBoard(seaHit);
      };

  private final EventHandler<YourTurnEvent> enableBoard =
      event -> {
        winning.setText(messageMap.get("playMessage"));
        shipBoard.setDisable(false);
      };

  private final EventHandler<YouMissedEvent> youMissed =
      event -> {
        lastField.missed();
        winning.setText(messageMap.get("waitMessage"));
        shipBoard.setDisable(true);
      };


  private final EventHandler<YouWinEvent> youWin =
      event -> {
        lastField.hit();
        winning.setText(messageMap.get("winMessage"));
        jack.setVisible(true);
      };

  private final EventHandler<YouLostEvent> youLost =
      event -> {
        winning.setText(messageMap.get("lossMessage"));
        sunk.setVisible(true);
      };

  private final EventHandler<YouHitEvent> youHit =
      event -> lastField.hit();

  private final EventHandler<SunkShipEvent> shipSunk =
      event -> {
        String[] positionsAsString = event.getMessage().split(POSITIONS_SEPARATOR);
        List<Integer> sunkShipPositions = Arrays.stream(positionsAsString)
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        ShipBoundariesPositions shipBoundariesPositions = new ShipBoundariesPositions(sea);
        shipBoundariesPositions.calculateShipBoundariesPositions(sunkShipPositions);
        shipBoundariesPositions.markSunkShip();
      };

  private final EventHandler<HitAgainEvent> hitAgain =
      event -> {
        lastField.hit();

        winning.setText(messageMap.get("hitAgainMessage"));
        shipBoard.setDisable(true);
      };

    private void addEventHandlerToTranscriptBtn() {
        transcriptBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            client.sendMessage("transcript");
//            String transcript = "";
//            FileWriter.write(transcript);
        });
    }

  public void setMessageProcessor(MessageProcessor messageProcessor) {
    this.processor = messageProcessor;
  }
}

