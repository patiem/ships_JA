package responses;

import actions.PlayAction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayResponse extends Response {

  public PlayResponse(){
    performAction = new PlayAction();
  }

  private ResponseHeader header = ResponseHeader.PLAY;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
