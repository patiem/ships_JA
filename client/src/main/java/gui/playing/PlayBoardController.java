package gui.playing;

import com.fasterxml.jackson.databind.ObjectMapper;
import connection.Client;
import events.UpdateWhenHitEvent;
import events.UpdateWhenMissedEvent;
import events.YouHitEvent;
import events.YouLostEvent;
import events.YouMissedEvent;
import events.YouWinEvent;
import events.YourTurnEvent;
import gui.fields.Mast;
import gui.fields.SeaField;
import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import json.JsonParserAdapter;
import model.FieldSize;
import model.MessageProcessor;
import model.Position;
import responses.Response;
import responses.ResponseHeader;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * It initializes and populates the playboard.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class PlayBoardController implements Initializable {

  private final Client client;
  private List<Position> positions;
  private final MessageProcessor processor;
  private SeaField lastField;

  @FXML
  private GridPane shipBoard;
  @FXML
  private GridPane targetBoard;
  @FXML
  private TextField winning;

  public PlayBoardController(Client client, MessageProcessor processor, List<Position> positions) {
    this.client = client;
    this.processor = processor;
    this.positions = positions;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateSeaWithSeaFields();
    shipBoard.setDisable(true);
    winning.setText("wait");
    populateOpponentBoardWithFleet();

    winning.addEventHandler(UpdateWhenHitEvent.UPDATE, updateBoardWhenHit);
    winning.addEventHandler(UpdateWhenMissedEvent.UPDATE_MISSED, updateBoardWhenMissed);
    winning.addEventHandler(YourTurnEvent.TURN, enableBoard);
    winning.addEventHandler(YouMissedEvent.WAIT, youMissed);
    winning.addEventHandler(YouWinEvent.WIN, youWin);
    winning.addEventHandler(YouLostEvent.LOST, youLost);
    winning.addEventHandler(YouHitEvent.HIT, youHit);

    new Thread(() -> {
      Boolean isRunning = true;
      while (isRunning) {
        String message = client.getMessage();
        JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();
        try {
          Response responseToProcess = jsonParserAdapter.parse(message, Response.class, new ObjectMapper());
          processor.processMessage(responseToProcess);
          ResponseHeader header = responseToProcess.getHeader();

          if (header == ResponseHeader.WIN || header == ResponseHeader.LOST) {
            isRunning = false;
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  private void populateSeaWithSeaFields() {
    for (int i = 0; i < 10; i++) {
      for (int n = 0; n < 10; n++) {
        SeaField field = new SeaField(i, n, FieldSize.BIG);
        field.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          client.sendMessage(field);
          field.marked();
          lastField = field;
        });
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

  //TODO: remove duplicated code from event handlers
  
  //EVENT HANDLERS

  private final EventHandler<UpdateWhenHitEvent> updateBoardWhenHit =
      event -> {
        Integer index = Integer.valueOf(event.getMessage());
        Position fieldPosition = new Position(index);
        System.out.println(fieldPosition);
        Platform.runLater(() -> {
          Mast hitMast = new Mast(fieldPosition.getRow(), fieldPosition.getColumn(), FieldSize.SMALL);
          hitMast.markedAsHit();
          targetBoard.getChildren().add(hitMast);
          GridPane.setConstraints(hitMast, fieldPosition.getColumn(), fieldPosition.getRow());
        });
      };

  private final EventHandler<UpdateWhenMissedEvent> updateBoardWhenMissed =
      event -> {
        Integer index = Integer.valueOf(event.getMessage());
        Position fieldPosition = new Position(index);
        System.out.println(fieldPosition);
        Platform.runLater(() -> {
          SeaField seaHit = new SeaField(fieldPosition.getRow(), fieldPosition.getColumn(), FieldSize.SMALL);
          seaHit.markedAsHit();
          targetBoard.getChildren().add(seaHit);
          GridPane.setConstraints(seaHit, fieldPosition.getColumn(), fieldPosition.getRow());
        });
      };

  private final EventHandler<YourTurnEvent> enableBoard =
      event -> {
        winning.setText("play");
        shipBoard.setDisable(false);
      };

  private final EventHandler<YouMissedEvent> youMissed =
      event -> {
        lastField.missed();
        winning.setText("wait");
        shipBoard.setDisable(true);
      };

  private final EventHandler<YouWinEvent> youWin =
      event -> {
        lastField.hit();
        winning.setText("You won");
      };

  private final EventHandler<YouLostEvent> youLost =
      event -> winning.setText("You lost");

  private final EventHandler<YouHitEvent> youHit =
      event -> lastField.hit();
}

