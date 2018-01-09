package model;

import gui.playing.*;
import javafx.scene.control.TextField;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageProcessor {

  private TextField dispatcher;

  //Emilia and Bartosz are working on removing this thing, but we won't marge their code before my
  //will be reviewed.

  public void processMessage(String message) {
    switch (message) {
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

  public void putObserverTextFieldForConnection(TextField textField) {
    this.dispatcher = textField;
  }
}
