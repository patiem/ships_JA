package connection;

import connection.chain.Chain;
import gui.playing.DispatcherAdapter;
import responses.Response;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska/Bartosz Pieczara
 * @version 1.5
 */
public class MessageProcessor {

  private DispatcherAdapter dispatcherAdapter;
  private Chain firstLinkInTheChain;

  public MessageProcessor(Chain firstLinkInTheChain, DispatcherAdapter dispatcher) {
    this.firstLinkInTheChain = firstLinkInTheChain;
    this.dispatcherAdapter = dispatcher;

  }

  public void processMessage(Response response) {
    firstLinkInTheChain.analyzeResponse(response, dispatcherAdapter);
  }
}
