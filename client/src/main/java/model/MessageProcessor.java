package model;

import events.UpdateWhenHitEvent;
import events.UpdateWhenMissedEvent;
import events.YouHitEvent;
import events.YouLostEvent;
import events.YouMissedEvent;
import events.YouWinEvent;
import events.YourTurnEvent;

import javafx.scene.control.TextField;
import responses.Response;
import responses.SuperiorMessage;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageProcessor extends SuperiorMessage{

//  private TextField dispatcher;

  public void processMessage2(Response response){
    response.makeMove(this);
  }

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
