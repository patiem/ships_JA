package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WinResponse extends Response {

  private ResponseHeader header = ResponseHeader.WIN;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
