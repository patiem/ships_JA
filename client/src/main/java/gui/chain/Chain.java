package gui.chain;

import javafx.scene.control.TextField;
import model.Shot;
import responses.Response;

public interface Chain {

  void setNextChain(Chain nextChain);

  void check(Response response, TextField textField);

  default String getShotAsString(Response response) {
    Shot shot = response.getShot().orElseThrow(IllegalArgumentException::new);
    return shot.toString();
  }

}
