package gui.starting;

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

  @FXML
  private TextField userName;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String name = "Your name";
    this.userName.setText(name);
  }
}
