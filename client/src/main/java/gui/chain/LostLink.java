package gui.chain;

import gui.events.YouLostEvent;
import javafx.scene.Node;
import responses.Response;
import responses.ResponseHeader;



public class LostLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {
    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, Node dispatcher) {
    if(response.getHeader() == ResponseHeader.LOST){
      dispatcher.fireEvent(new YouLostEvent());
    } else {
      nextInChain.check(response, dispatcher);
    }
  }
}