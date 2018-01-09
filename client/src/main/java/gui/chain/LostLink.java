package gui.chain;

import gui.events.YouLostEvent;
import javafx.scene.control.TextField;
import responses.Response;
import responses.ResponseHeader;



public class LostLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, TextField dispatcher) {
    if(response.getHeader() == ResponseHeader.LOST){
      dispatcher.fireEvent(new YouLostEvent());
    } else {
      nextInChain.check(response,dispatcher);
    }
  }
}