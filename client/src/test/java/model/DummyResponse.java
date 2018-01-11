package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import responses.Response;
import responses.ResponseHeader;

public class DummyResponse extends Response {

  private ResponseHeader header = ResponseHeader.NONE;

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
