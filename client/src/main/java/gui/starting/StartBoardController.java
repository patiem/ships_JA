package gui.starting;

import connection.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import messages.LanguageVersion;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * It initializes the playboard.
 *
 * @version 1.5
 */
class StartBoardController implements Initializable {

  private final Client client;
  private LanguageVersion languageVersion = new LanguageVersion();

  @FXML
  private TextField userName;

  @FXML
  private Label nameLabel;

  @FXML
  private Button connectButton;


  StartBoardController(Client client) {
    this.client = client;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.userName.setText(languageVersion.getName());
    this.nameLabel.setText(languageVersion.getNameLabel());
    this.connectButton.setText(languageVersion.getConnect());
    }
}
