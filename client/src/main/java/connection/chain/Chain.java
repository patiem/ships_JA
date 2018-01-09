package connection.chain;

import gui.playing.DispatcherAdapter;
import model.Shot;
import responses.Response;

public interface Chain {

  void setNextChain(Chain nextChain);

  void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter);

  default String getShotAsString(Response response) {
    Shot shot = response.getShot().orElseThrow(IllegalArgumentException::new);
    return shot.toString();
  }
}
