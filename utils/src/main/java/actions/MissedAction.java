package actions;

import events.YouMissedEvent;
import responses.PerformAction;
import responses.SuperiorMessage;

public class MissedAction implements PerformAction{

  @Override
  public void act(SuperiorMessage message) {
    message.dispatcher.fireEvent(new YouMissedEvent());
  }

}
