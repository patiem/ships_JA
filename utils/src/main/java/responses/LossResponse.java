package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LossResponse implements Response {

  private ResponseHeader header = ResponseHeader.LOST;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
