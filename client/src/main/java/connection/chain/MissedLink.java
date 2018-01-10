package connection.chain;

import gui.events.YouMissedEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class MissedLink implements Chain {

  private Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.MISSED) {
      dispatcherAdapter.fireEvent(new YouMissedEvent());
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}
