package model;

import gui.fields.SeaField;
import gui.starting.ConnectEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageReactor {

  private Button nextButton;
  private SeaField lastField;
  private TextField textField;

  public void reactOnMessage(String message) {          //TODO: change string to enum
    switch (message) {
      case "CON":
        nextButton.fireEvent(new ConnectEvent("CON"));
        break;
      case "HIT":
        lastField.hit();
        break;
      case "MISSED":
        lastField.missed();
        break;
      case "HIT_AGAIN":
        break;
      case "WIN":
        lastField.hit();
        textField.setText("You won");
        break;
      default:
        break;
    }
  }

  public void reactOnMessage(SeaField lastField, String message) {
    this.lastField = lastField;
    reactOnMessage(message);
  }

  public void putObserverButtonForConnection(Button button) {
    this.nextButton = button;
  }

  public void putObserverTextFieldForConnection(TextField textField) {
    this.textField = textField;
  }
}
