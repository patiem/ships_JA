package actions;

import events.UpdateWhenHitEvent;
import responses.PerformAction;
import responses.SuperiorMessage;

public class OpphitAction implements PerformAction {

  @Override
  public void act(SuperiorMessage message) {
    message.processOpponentHit();
  }
}
