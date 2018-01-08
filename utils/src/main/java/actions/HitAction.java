package actions;

import events.YouHitEvent;
import responses.PerformAction;
import responses.SuperiorMessage;

public class HitAction implements PerformAction{

  @Override
  public void act(SuperiorMessage message) {
    message.dispatcher.fireEvent(new YouHitEvent());
  }
}
