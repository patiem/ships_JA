package gui.chain;

import javafx.scene.Node;
import model.Shot;
import responses.Response;

public interface Chain {

  void setNextChain(Chain nextChain);

  void check(Response response, Node dispatcher);

  default String getShotAsString(Response response) {
    Shot shot = response.getShot().orElseThrow(IllegalArgumentException::new);
    return shot.toString();
  }

}
