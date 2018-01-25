package connection.chain;

import com.fasterxml.jackson.annotation.JsonProperty;
import responses.Response;
import responses.ResponseHeader;

public class DummyResponse implements Response {

  private ResponseHeader header = ResponseHeader.NONE;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
