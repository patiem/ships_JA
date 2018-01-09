package gui.chain;

import gui.events.YourTurnEvent;
import model.MessageProcessor;
import responses.Response;
import responses.ResponseHeader;

public class PlayLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, MessageProcessor messageProcessor) {
    if(response.getHeader() == ResponseHeader.PLAY){
      messageProcessor.getDispatcher().fireEvent(new YourTurnEvent());
    } else {
      nextInChain.check(response,messageProcessor);
    }
  }
}