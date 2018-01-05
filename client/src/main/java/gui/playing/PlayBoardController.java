package gui.playing;

import connection.Client;
import gui.fields.Mast;
import gui.fields.SeaField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.FieldSize;
import model.MessageProcessor;
import model.Position;

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
  }

  private void populateSeaWithSeaFields() {
    for (int i = 0; i < 10; i++) {
      for (int n = 0; n < 10; n++) {
        SeaField field = new SeaField(i, n, FieldSize.BIG);
        field.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          client.sendMessage(field);
          field.marked();
          processor.setLastField(field);
        });
        shipBoard.getChildren().add(field);
        GridPane.setConstraints(field, i, n);
      }
    }

    for (Position position : positions) {
      Mast smallMast = new Mast(position.getRow(), position.getColumn(), FieldSize.SMALL);
      targetBoard.getChildren().add(smallMast);
      GridPane.setConstraints(smallMast, position.getColumn(), position.getRow());
    }

    winning.addEventHandler(UpdateEventWhenHit.UPDATE, event -> {
      Integer index = Integer.valueOf(event.getMessage());
      Position fieldPosition = new Position(index);
      System.out.println(fieldPosition);
      Platform.runLater(() -> {
        Mast hitMast = new Mast(fieldPosition.getRow(), fieldPosition.getColumn(), FieldSize.SMALL);
        hitMast.markedAsHit();
        targetBoard.getChildren().add(hitMast);
        GridPane.setConstraints(hitMast, fieldPosition.getColumn(), fieldPosition.getRow());
      });
    });

    winning.addEventHandler(UpdateEventWhenMissed.MISSED, event -> {
      Integer index = Integer.valueOf(event.getMessage());
      Position fieldPosition = new Position(index);
      System.out.println(fieldPosition);

      Platform.runLater(() -> {
        SeaField seaHit = new SeaField(fieldPosition.getRow(), fieldPosition.getColumn(), FieldSize.SMALL);
        seaHit.markedAsHit();
        targetBoard.getChildren().add(seaHit);
        GridPane.setConstraints(seaHit, fieldPosition.getColumn(), fieldPosition.getRow());
      });
    });

    new Thread(() -> {
      Boolean isRunning = true;
      while(isRunning) {
        String message = client.getMessage();
        processor.processMessage(message);
        if (message.equals("WIN") || message.equals("LOST")) isRunning = false;
      }
    }).start();
  }
}

