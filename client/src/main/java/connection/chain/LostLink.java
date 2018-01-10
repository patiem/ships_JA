package connection.chain;

import gui.events.YouLostEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class LostLink implements Chain {

  private Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.LOST) {
      dispatcherAdapter.fireEvent(new YouLostEvent());
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}