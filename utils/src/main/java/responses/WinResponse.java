package responses;

import actions.WinAction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WinResponse extends Response {

  public WinResponse() {
    performAction = new WinAction();
  }


  private ResponseHeader header = ResponseHeader.WIN;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
