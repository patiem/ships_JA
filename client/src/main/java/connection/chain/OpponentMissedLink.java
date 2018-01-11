package connection.chain;

import gui.events.UpdateWhenMissedEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class OpponentMissedLink extends Chain {

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.OPPMISSED) {
      String shotAsString = getShotAsString(response);
      dispatcherAdapter.fireEvent(new UpdateWhenMissedEvent(shotAsString));
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}