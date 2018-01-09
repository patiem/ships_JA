package model;

import gui.chain.Chain;
import gui.chain.ChainConfigFactory;
import responses.HitResponse;

import static org.testng.Assert.*;

public class MessageProcessorTest {

  public void whenReceivedHitLinkObjectThenCallTheAppropriateEvent() {

    HitResponse hitResponse = new HitResponse();
    Chain firstLink = ChainConfigFactory.configureChainOfResponsibilities();
    MessageProcessor messageProcessor = new MessageProcessor(firstLink);
    messageProcessor.processMessage(hitResponse);





  }

}