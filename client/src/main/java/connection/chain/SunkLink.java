package connection.chain;

import gui.events.SunkShipEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class SunkLink extends Chain {

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.SUNK) {
      dispatcherAdapter.fireEvent(new SunkShipEvent());
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }

}
