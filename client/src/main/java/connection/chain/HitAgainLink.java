package connection.chain;

import gui.events.HitAgainEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class HitAgainLink extends Chain {
  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.HIT_AGAIN) {
      String shotAsString = getShotAsString(response);
      dispatcherAdapter.fireEvent(new HitAgainEvent(shotAsString));
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}
