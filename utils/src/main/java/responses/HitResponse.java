package responses;

import actions.HitAction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HitResponse extends Response {
  private ResponseHeader header = ResponseHeader.HIT;

  public HitResponse(){
    performAction = new HitAction();
  }

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }

}
