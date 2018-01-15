package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Shot;

import java.util.Optional;

public class OpponentMissedResponse implements Response {

  private ResponseHeader header = ResponseHeader.OPPMISSED;
  private Shot shot;

  @JsonCreator
  public OpponentMissedResponse(@JsonProperty("shot") Shot shot) {
    this.shot = shot;
  }

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }

  @JsonProperty("shot")
  @Override
  public Optional<Shot> getShot() {
    return Optional.of(shot);
  }
}
