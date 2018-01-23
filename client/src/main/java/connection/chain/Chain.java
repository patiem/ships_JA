package connection.chain;

import gui.playing.DispatcherAdapter;
import common.model.Shot;
import responses.Response;

public abstract class Chain {

  Chain nextInChain;

  public abstract void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter);

  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  String getShotAsString(Response response) {
    Shot shot = response.getShot().orElseThrow(IllegalArgumentException::new);
    return shot.toString();
  }
}
