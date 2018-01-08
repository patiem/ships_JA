package model;


import actions.HitAction;
import events.UpdateWhenHitEvent;
import events.UpdateWhenMissedEvent;
import events.YouHitEvent;
import events.YouLostEvent;
import events.YouMissedEvent;
import events.YouWinEvent;
import events.YourTurnEvent;
import gui.act.HitInstruction;
import gui.act.Instruction;
import javafx.scene.control.TextField;
import responses.*;

import java.util.EnumMap;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageProcessor extends SuperiorMessage {

  private TextField dispatcher;
  Response response;
  private EnumMap<ResponseHeader,Instruction> map;

  public void processMessage3(Response response) {
    this.response = response;
    response.makeMove(this);
  }

  public void processMessage(Response response) {
    map = new EnumMap<>(ResponseHeader.class);
    populateMap(response);
    map.get(response.getHeader()).perform(dispatcher);
  }

  private void populateMap(Response response) {
    map.put(ResponseHeader.HIT, new HitInstruction());
    map.put(ResponseHeader.MISSED, new MissedResponse());
    map.put(ResponseHeader.WIN, new WinResponse());
    map.put(ResponseHeader.PLAY, new PlayResponse());
    map.put(ResponseHeader.LOST, new LossResponse());
    map.put(ResponseHeader.OPPHIT, new OpponentHitResponse(response.getShot().get()));
    map.put(ResponseHeader.OPPMISSED, new OpponentMissedResponse(response.getShot().get()));

  }

  public void processMessage2(Response response) {
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
        processOpponentHit();
        break;
      case OPPMISSED:
        processOpponentMissed();
        break;
      default:
        break;
    }
  }

  public void putObserverTextFieldForConnection(TextField textField) {
    this.dispatcher = textField;
  }

  private void processOpponentMissed() {
    String shotAsString = getShotAsString(response);
    dispatcher.fireEvent(new UpdateWhenMissedEvent(shotAsString));
  }

  private void processOpponentHit() {
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
