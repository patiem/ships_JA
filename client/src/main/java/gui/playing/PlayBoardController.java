package gui.playing;

import connection.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.MessageProcessor;
import gui.fields.SeaField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * It initializes and populates the playboard.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class PlayBoardController implements Initializable {

  private final Client client;
  private final MessageProcessor reactor;

  @FXML
  private GridPane shipBoard;

  public PlayBoardController(Client client, MessageProcessor reactor) {
    this.client = client;
    this.reactor = reactor;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateSeaWithSeaFields();
  }

  private void populateSeaWithSeaFields() {
    for (int i = 0; i < 10; i++) {
      for (int n = 0; n < 10; n++) {
        SeaField field = new SeaField(i, n);
        field.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          client.sendMessage(field);
          field.marked();
          reactor.processMessage(field, client.getMessage());
        });
        shipBoard.getChildren().add(field);
        GridPane.setConstraints(field, i, n);
      }
    }
  }
}

