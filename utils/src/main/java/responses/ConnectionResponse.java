package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectionResponse implements Response {

  private ResponseHeader header = ResponseHeader.CON;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
