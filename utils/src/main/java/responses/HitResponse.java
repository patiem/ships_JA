package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitResponse extends Response {
  private ResponseHeader header = ResponseHeader.HIT;


  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
