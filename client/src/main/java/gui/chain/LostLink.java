package gui.chain;


import gui.events.YouLostEvent;
import model.MessageProcessor;
import responses.Response;
import responses.ResponseHeader;

public class LostLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, MessageProcessor messageProcessor) {
    if(response.getHeader() == ResponseHeader.LOST){
      messageProcessor.getDispatcher().fireEvent(new YouLostEvent());
    } else {
      nextInChain.check(response,messageProcessor);
    }
  }
}