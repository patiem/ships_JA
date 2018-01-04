package gui.playing;

import connection.Client;
import gui.fields.Mast;
import gui.fields.SeaField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.FieldSize;
import model.Position;
import model.MessageProcessor;

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
          processor.processMessage(field, client.getMessage());
        });
        shipBoard.getChildren().add(field);
        GridPane.setConstraints(field, i, n);
      }
    }

    for (Position position : positions) {
      Mast smallMast = new Mast(position.getRow(), position.getColumn(), FieldSize.SMALL);
      targetBoard.getChildren().add(smallMast);
      GridPane.setConstraints(smallMast, position.getRow(), position.getColumn());
    }
  }
}

