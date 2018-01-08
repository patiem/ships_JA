package responses;

import actions.LostAction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LossResponse extends Response {

  public LossResponse() {
    performAction = new LostAction();
  }

  private ResponseHeader header = ResponseHeader.LOST;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
