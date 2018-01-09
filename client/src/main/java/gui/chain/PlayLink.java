package gui.chain;

import gui.events.YourTurnEvent;
import javafx.scene.control.TextField;
import responses.Response;
import responses.ResponseHeader;

public class PlayLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, TextField dispatcher) {
    if(response.getHeader() == ResponseHeader.PLAY){
      dispatcher.fireEvent(new YourTurnEvent());
    } else {
      nextInChain.check(response,dispatcher);
    }
  }
}