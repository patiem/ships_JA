package connection.chain;

import gui.events.YourTurnEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class PlayLink implements Chain {

  private Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.PLAY) {
      dispatcherAdapter.fireEvent(new YourTurnEvent());
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}