package gui.chain;
import javafx.scene.control.TextField;
import gui.events.UpdateWhenHitEvent;
import responses.Response;
import responses.ResponseHeader;

public class OpphitLink implements Chain {

  private  Chain nextInChain;

  @Override
  public void setNextChain(Chain nextChain) {

    nextInChain = nextChain;
  }

  @Override
  public void check(Response response, TextField dispatcher) {
    if(response.getHeader() == ResponseHeader.OPPHIT){
      String shotAsString = getShotAsString(response);
      dispatcher.fireEvent(new UpdateWhenHitEvent(shotAsString));
    } else {
      nextInChain.check(response,dispatcher);
    }
  }

}