package gui.chain;

import gui.events.YouMissedEvent;
import javafx.scene.control.TextField;
import responses.Response;
import responses.ResponseHeader;

public class MissedLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, TextField dispatcher) {
    if(response.getHeader() == ResponseHeader.MISSED){
      dispatcher.fireEvent(new YouMissedEvent());
    } else {
      nextInChain.check(response,dispatcher);
    }
  }
}
