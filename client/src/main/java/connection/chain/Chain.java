package connection.chain;

import gui.events.YouHitEvent;
import gui.playing.DispatcherAdapter;
import model.ShipModel;
import model.Shot;
import responses.Response;
import responses.ResponseHeader;

public abstract class Chain {

  Chain nextInChain;

  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.HIT) {
      dispatcherAdapter.fireEvent(new YouHitEvent());
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }

  String getShotAsString(Response response) {
    Shot shot = response.getShot().orElseThrow(IllegalArgumentException::new);
    return shot.toString();
  }
}
