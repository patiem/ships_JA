package starting;

import connection.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class StartBoardController implements Initializable {

  private final Client client;

  @FXML
  private TextField userName;

  @FXML
  private TextField ipNumber;

  public StartBoardController(Client client) {
    this.client = client;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String userName = "Your name";
    this.userName.setText(userName);
    String ipNumber = "localhost";
    this.ipNumber.setText(ipNumber);
    client.setup(ipNumber);
  }
}
