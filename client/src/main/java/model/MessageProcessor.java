package model;

import gui.chain.Chain;
import gui.chain.HitPart;
import gui.chain.LostPart;
import gui.chain.MissedPart;
import gui.chain.OpphitPart;
import gui.chain.OppmissPart;
import gui.chain.PlayPart;
import gui.chain.WinPart;
import javafx.scene.control.TextField;
import responses.*;


/**
 * It calls different methods depending on the message that has been sent form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class MessageProcessor extends SuperiorMessage {

  private TextField dispatcher;

  public void processMessage(Response response) { // TODO: reflect on how to create these chains
    Chain chain1 = new HitPart();
    Chain chain2 = new MissedPart();
    Chain chain3 = new WinPart();
    Chain chain4 = new PlayPart();
    Chain chain5 = new LostPart();
    Chain chain6 = new OpphitPart();
    Chain chain7 = new OppmissPart();

    chain1.setNextChain(chain2);
    chain2.setNextChain(chain3);
    chain3.setNextChain(chain4);
    chain4.setNextChain(chain5);
    chain5.setNextChain(chain6);
    chain6.setNextChain(chain7);

    chain1.check(response,this);

  }

  public void putObserverTextFieldForConnection(TextField textField) {
    this.dispatcher = textField;
  }

  public TextField getDispatcher() {
    return dispatcher;
  }
}
