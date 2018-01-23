package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import common.model.Shot;

import java.util.Optional;

public class HitAgainResponse implements Response {
  private ResponseHeader header = ResponseHeader.HIT_AGAIN;
  private Shot shot;

  @JsonCreator
  public HitAgainResponse(@JsonProperty("shot") Shot shot) {
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
