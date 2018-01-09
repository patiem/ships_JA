package gui.chain;

import gui.events.UpdateWhenHitEvent;
import model.MessageProcessor;
import responses.Response;
import responses.ResponseHeader;

public class OpphitPart implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, MessageProcessor messageProcessor) {
    if(response.getHeader() == ResponseHeader.OPPHIT){
      String shotAsString = getShotAsString(response);
      messageProcessor.getDispatcher().fireEvent(new UpdateWhenHitEvent(shotAsString));
    } else {
      nextInChain.check(response,messageProcessor);
    }
  }

}