package gui.chain;

import gui.events.YouWinEvent;

import model.MessageProcessor;
import responses.Response;
import responses.ResponseHeader;

public class WinLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, MessageProcessor messageProcessor) {
    if(response.getHeader() == ResponseHeader.WIN){
      messageProcessor.getDispatcher().fireEvent(new YouWinEvent());
    } else {
      nextInChain.check(response,messageProcessor);
    }
  }
}