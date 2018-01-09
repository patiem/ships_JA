package gui.chain;

import gui.events.YouHitEvent;
import model.MessageProcessor;
import responses.Response;
import responses.ResponseHeader;

public class HitPart implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, MessageProcessor messageProcessor) {
    if(response.getHeader() == ResponseHeader.HIT){
      messageProcessor.getDispatcher().fireEvent(new YouHitEvent());
    } else {
      nextInChain.check(response,messageProcessor);
    }
  }
}
