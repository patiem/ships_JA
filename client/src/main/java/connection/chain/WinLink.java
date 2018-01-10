package connection.chain;

import gui.events.YouWinEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class WinLink extends Chain {

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.WIN) {
      dispatcherAdapter.fireEvent(new YouWinEvent());
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}