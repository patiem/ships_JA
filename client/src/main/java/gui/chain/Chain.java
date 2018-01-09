package gui.chain;

import javafx.scene.control.TextField;
import model.MessageProcessor;
import model.Shot;
import responses.Response;

public interface Chain {

  void setNextChain(Chain nextChain);

  void check(Response response, MessageProcessor messageProcessor);


  default String getShotAsString(Response response) {
    Shot shot = response.getShot().orElseThrow(IllegalArgumentException::new);
    return shot.toString();
  }

}
