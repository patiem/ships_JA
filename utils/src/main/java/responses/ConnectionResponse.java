package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectionResponse extends Response {

  private ResponseHeader header = ResponseHeader.CON;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
