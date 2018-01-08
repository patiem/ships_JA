package actions;

import events.UpdateWhenMissedEvent;
import responses.PerformAction;
import responses.SuperiorMessage;

public class OppMissAction implements PerformAction{

  @Override
  public void act(SuperiorMessage message) {
    message.dispatcher.fireEvent(new UpdateWhenMissedEvent("oppmissed"));
  }
}
