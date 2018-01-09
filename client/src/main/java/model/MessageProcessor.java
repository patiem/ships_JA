package model;

import gui.chain.Chain;
import javafx.scene.control.TextField;
import responses.*;


/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageProcessor {

  private TextField dispatcher;
  private Chain firstLinkInTheChain;

  public MessageProcessor(Chain firstLinkInTheChain) {
    this.firstLinkInTheChain = firstLinkInTheChain;
  }

  public void processMessage(Response response) { // TODO: reflect on how to create these chains
    firstLinkInTheChain.check(response,this);
  }

  public void putObserverTextFieldForConnection(TextField textField) {
    this.dispatcher = textField;
  }

  public TextField getDispatcher() {
    return dispatcher;
  }

//  private void configureChainOfResponsibilities() {
//    firstLinkInTheChain = new HitLink();
//    Chain chain2 = new MissedLink();
//    Chain chain3 = new WinLink();
//    Chain chain4 = new PlayLink();
//    Chain chain5 = new LostLink();
//    Chain chain6 = new OpphitLink();
//    Chain chain7 = new OppmissLink();
//
//    firstLinkInTheChain.setNextChain(chain2);
//    chain2.setNextChain(chain3);
//    chain3.setNextChain(chain4);
//    chain4.setNextChain(chain5);
//    chain5.setNextChain(chain6);
//    chain6.setNextChain(chain7);
//  }

}
