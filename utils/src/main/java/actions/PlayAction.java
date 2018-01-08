package actions;

import events.YourTurnEvent;
import responses.PerformAction;
import responses.SuperiorMessage;

public class PlayAction implements PerformAction {

  @Override
  public void act(SuperiorMessage message) {
    message.dispatcher.fireEvent(new YourTurnEvent());
  }
}
