package gui.chain;

import gui.events.YouHitEvent;
import javafx.scene.control.TextField;
import responses.Response;
import responses.ResponseHeader;

public class HitLink implements Chain {

  private Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, TextField dispatcher) {
    if(response.getHeader() == ResponseHeader.HIT){
      dispatcher.fireEvent(new YouHitEvent());
    } else {
      nextInChain.check(response,dispatcher);
    }
  }
}
