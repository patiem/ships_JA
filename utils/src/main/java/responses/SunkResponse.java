package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SunkResponse extends Response {
  private ResponseHeader header = ResponseHeader.SUNK;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}