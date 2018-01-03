package starting;

import connection.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


class StartBoardController implements Initializable {

  private final Client client;

  @FXML
  private TextField userName;

  @FXML
  private TextField ipNumber;

  StartBoardController(Client client) {
    this.client = client;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String name = "Your name";
    this.userName.setText(name);
    String localhost = "localhost";
    this.ipNumber.setText(localhost);
    client.setup(localhost);
  }
}
