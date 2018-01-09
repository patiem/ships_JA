package model;

import gui.chain.Chain;
import javafx.scene.control.TextField;
import responses.*;

/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska/Bartosz Pieczara
 * @version 1.5
 */
public class MessageProcessor {

  private TextField dispatcher;

  private Chain firstLinkInTheChain;

  public MessageProcessor(Chain firstLinkInTheChain) {
    this.firstLinkInTheChain = firstLinkInTheChain;
  }

  public void processMessage(Response response) { // TODO: reflect on how to create these chains
    firstLinkInTheChain.check(response, dispatcher);

  }

  public void putObserverTextFieldForConnection(TextField textField) {
    this.dispatcher = textField;
  }


  public TextField getDispatcher() {
    return dispatcher;
  }

}
