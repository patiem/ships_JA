package model;

import connection.Client;
import gui.fields.SeaField;
import gui.playing.*;
import gui.starting.ConnectEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Map;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageProcessor {

  private Button nextButton;
  private SeaField lastField;
  private TextField dispatcher;
  private Client client;
  private Map<String, MessageAction> actionBook;

  public void registerClassAction(String header, MessageAction action) {
    actionBook.put(header, action);
  }

  public void actOnMessage(String message) {

  }

  public void processMessage(String message) {
    switch (message) {
      case "CON":
        nextButton.fireEvent(new ConnectEvent());
        break;
      case "HIT":
        dispatcher.fireEvent(new YouHitEvent());
        break;
      case "MISSED":
        dispatcher.fireEvent(new YouMissedEvent());
        break;
      case "WIN":
        dispatcher.fireEvent(new YouWinEvent());
        break;
      case "PLAY":
        dispatcher.fireEvent(new YourTurnEvent());
        break;
      case "LOST":
        dispatcher.fireEvent(new YouLostEvent());
        break;
      default:
        if (message.contains("OPPHIT")) processOpponentHit(message);
        if (message.contains("OPPMISSED")) processOpponentMissed(message);
        break;
    }
  }

  private void processOpponentMissed(String message) {
    String fieldIndex = message.split(" ")[1];
    dispatcher.fireEvent(new UpdateWhenMissedEvent(fieldIndex));
  }

  private void processOpponentHit(String message) {
    String fieldIndex = message.split(" ")[1];
    dispatcher.fireEvent(new UpdateWhenHitEvent(fieldIndex));
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
    this.dispatcher = textField;
  }

  public void putClient(Client client) {
    this.client = client;
  }
}
