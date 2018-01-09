package gui.chain;

import gui.events.UpdateWhenMissedEvent;

import model.MessageProcessor;
import responses.Response;
import responses.ResponseHeader;

public class OppmissLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, MessageProcessor messageProcessor) {
    if(response.getHeader() == ResponseHeader.OPPMISSED){
      String shotAsString = getShotAsString(response);
      messageProcessor.getDispatcher().fireEvent(new UpdateWhenMissedEvent(shotAsString));
    } else {
      nextInChain.check(response,messageProcessor);
    }
  }
}