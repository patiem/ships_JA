package actions;

import events.YouLostEvent;
import responses.PerformAction;
import responses.SuperiorMessage;

public class LostAction implements PerformAction{

  @Override
  public void act(SuperiorMessage message) {
    message.dispatcher.fireEvent(new YouLostEvent());
  }

}
