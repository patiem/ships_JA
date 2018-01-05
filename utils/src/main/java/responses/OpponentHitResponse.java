package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Shot;

public class OpponentHitResponse implements Response {

  private Shot shot;


  @JsonCreator
  public OpponentHitResponse(@JsonProperty("shot")Shot shot) {
    this.shot = shot;
  }

  private final String HEADER = "OPPHIT"; // TODO: check JSON

  @JsonProperty("header")
  @Override
  public String getHeader() {
    return HEADER;
  }
}
