package gui.starting;

import gui.OutputChannelDispatcher;
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

  private LanguageVersion languageVersion = new LanguageVersion();
  private OutputChannelDispatcher outputChannelDispatcher = new  OutputChannelDispatcher();

  @FXML
  private TextField userName;

  @FXML
  private Label nameLabel;

  @FXML
  private Button connectButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String nameMessage = languageVersion.getMessage("name");
    String nameLabel = languageVersion.getMessage("nameLabel");
    String connect = languageVersion.getMessage("connect");

    this.userName.setText(nameMessage);
    this.nameLabel.setText(nameLabel);
    this.connectButton.setText(connect);

    outputChannelDispatcher.printToDesiredOutput(nameMessage);
    outputChannelDispatcher.printToDesiredOutput(nameLabel);
    outputChannelDispatcher.printToDesiredOutput(connect);
  }
}
