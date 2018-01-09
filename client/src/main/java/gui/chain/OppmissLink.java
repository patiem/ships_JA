package gui.chain;

import gui.events.UpdateWhenMissedEvent;

import javafx.scene.control.TextField;
import responses.Response;
import responses.ResponseHeader;

public class OppmissLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, TextField dispatcher) {
    if(response.getHeader() == ResponseHeader.OPPMISSED){
      String shotAsString = getShotAsString(response);
      dispatcher.fireEvent(new UpdateWhenMissedEvent(shotAsString));
    } else {
      nextInChain.check(response,dispatcher);
    }
  }
}