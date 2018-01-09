package gui.chain;

import gui.events.YouWinEvent;

import javafx.scene.control.TextField;
import responses.Response;
import responses.ResponseHeader;

public class WinLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, TextField dispatcher) {
    if(response.getHeader() == ResponseHeader.WIN){
      dispatcher.fireEvent(new YouWinEvent());
    } else {
      nextInChain.check(response,dispatcher);
    }
  }
}