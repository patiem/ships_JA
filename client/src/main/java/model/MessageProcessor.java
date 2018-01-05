package model;

import connection.Client;
import gui.fields.SeaField;
import gui.playing.UpdateEventWhenHit;
import gui.playing.UpdateEventWhenMissed;
import gui.starting.ConnectEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageProcessor {

  private Button nextButton;
  private SeaField lastField;
  private TextField textField;
  private Client client;

  public void processMessage(String message) {
    switch (message) {
      case "CON":
        nextButton.fireEvent(new ConnectEvent());
        break;
      case "HIT":
        lastField.hit();
        break;
      case "MISSED":
        lastField.missed();
        textField.setText("wait");
        break;
      case "HIT_AGAIN":
        break;
      case "WIN":
        lastField.hit();
        textField.setText("You won");
        break;
      case "PLAY":
        textField.setText("play");
        break;
      case "LOST":
        textField.setText("You lost");
        break;
      default:
        if (message.contains("OPPHIT")) processOpponentHit(message);
        if (message.contains("OPPMISSED")) processOpponentMissed(message);
        break;
    }
  }

  private void processOpponentMissed(String message) {
    String fieldIndex = message.split(" ")[1];
    textField.fireEvent(new UpdateEventWhenMissed(fieldIndex));
  }

  private void processOpponentHit(String message) {
    String fieldIndex = message.split(" ")[1];
    textField.fireEvent(new UpdateEventWhenHit(fieldIndex));
  }

  public void setLastField(SeaField lastField) {
    this.lastField = lastField;
  }

  public void processMessage(SeaField lastField, String message) {
    this.lastField = lastField;
    processMessage(message);
  }

  public void putObserverButtonForConnection(Button button) {
    this.nextButton = button;
  }

  public void putObserverTextFieldForConnection(TextField textField) {
    this.textField = textField;
  }

  public void putClient(Client client) {
    this.client = client;
  }
}
