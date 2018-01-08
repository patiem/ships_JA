package actions;

import events.YouWinEvent;
import responses.PerformAction;
import responses.SuperiorMessage;

public class WinAction implements PerformAction {

  @Override
  public void act(SuperiorMessage message) {
    message.dispatcher.fireEvent(new YouWinEvent());
  }

}
