package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Shot;

public class OpponentHitResponse extends Response {

  private ResponseHeader header = ResponseHeader.OPPHIT;
  private Shot shot;

  @JsonCreator
  public OpponentHitResponse(@JsonProperty("shot")Shot shot) {
    this.shot = shot;
  }

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
