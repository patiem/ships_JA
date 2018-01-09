package gui.chain;

import gui.events.YouMissedEvent;
import model.MessageProcessor;
import responses.Response;
import responses.ResponseHeader;

public class MissedPart implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, MessageProcessor messageProcessor) {
    if(response.getHeader() == ResponseHeader.MISSED){
      messageProcessor.getDispatcher().fireEvent(new YouMissedEvent());
    } else {
      nextInChain.check(response,messageProcessor);
    }
  }
}
