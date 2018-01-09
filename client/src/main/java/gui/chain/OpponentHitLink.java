package gui.chain;
import gui.events.UpdateWhenHitEvent;
import javafx.scene.Node;
import responses.Response;
import responses.ResponseHeader;

public class OpponentHitLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, Node dispatcher) {
    if(response.getHeader() == ResponseHeader.OPPHIT){
      String shotAsString = getShotAsString(response);
      dispatcher.fireEvent(new UpdateWhenHitEvent(shotAsString));
    } else {
      nextInChain.check(response, dispatcher);
    }
  }

}