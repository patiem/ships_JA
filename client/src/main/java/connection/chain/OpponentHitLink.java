package connection.chain;

import gui.events.UpdateWhenHitEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class OpponentHitLink extends Chain {

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.OPPHIT) {
      String shotAsString = getShotAsString(response);
      dispatcherAdapter.fireEvent(new UpdateWhenHitEvent(shotAsString));
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}