package gui.starting;

import connection.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * It initializes the playboard.
 *
 * @version 1.5
 */
class StartBoardController implements Initializable {

  private final Client client;

  @FXML
  private TextField userName;


  StartBoardController(Client client) {
    this.client = client;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String name = "Your name";
    this.userName.setText(name);
    }
}
