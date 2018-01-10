package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayResponse extends Response {

  private ResponseHeader header = ResponseHeader.PLAY;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
