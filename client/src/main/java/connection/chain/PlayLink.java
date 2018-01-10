package connection.chain;

import gui.events.YourTurnEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class PlayLink extends Chain {

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.PLAY) {
      dispatcherAdapter.fireEvent(new YourTurnEvent());
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}