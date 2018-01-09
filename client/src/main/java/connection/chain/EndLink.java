package connection.chain;

import gui.playing.DispatcherAdapter;
import responses.Response;

public class EndLink implements Chain {

  @Override
  public void setNextChain(Chain nextChain) { //TODO: is it a good/bad practice?
    throw new UnsupportedOperationException();
  }

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    String message = String.format("Unknown response with header: %s", response.getHeader());
    throw new IllegalArgumentException(message);
  }
}
