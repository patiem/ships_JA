package model;

import gui.playing.UpdateWhenHitEvent;
import gui.playing.UpdateWhenMissedEvent;
import gui.playing.YouHitEvent;
import gui.playing.YouLostEvent;
import gui.playing.YouMissedEvent;
import gui.playing.YouWinEvent;
import gui.playing.YourTurnEvent;
import javafx.scene.control.TextField;
import responses.Response;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageProcessor {

  private TextField dispatcher;

  public void processMessage(Response response) {
    switch (response.getHeader()) {
      case HIT:
        dispatcher.fireEvent(new YouHitEvent());
        break;
      case MISSED:
        dispatcher.fireEvent(new YouMissedEvent());
        break;
      case WIN:
        dispatcher.fireEvent(new YouWinEvent());
        break;
      case PLAY:
        dispatcher.fireEvent(new YourTurnEvent());
        break;
      case LOST:
        dispatcher.fireEvent(new YouLostEvent());
        break;
      case OPPHIT:
        processOpponentHit(response);
        break;
      case OPPMISSED:
        processOpponentMissed(response);
        break;
      default:
        break;
    }
  }

  public void putObserverTextFieldForConnection(TextField textField) {
    this.dispatcher = textField;
  }

  private void processOpponentMissed(Response response) {
    String shotAsString = getShotAsString(response);
    dispatcher.fireEvent(new UpdateWhenMissedEvent(shotAsString));
  }

  private void processOpponentHit(Response response) {
    String shotAsString = getShotAsString(response);
    dispatcher.fireEvent(new UpdateWhenHitEvent(shotAsString));
  }

  private String getShotAsString(Response response) {
    Shot shot = response.getShot().orElseThrow(IllegalArgumentException::new);
    System.out.println(shot);
    System.out.println(shot.toString());
    return shot.toString();
  }
}
