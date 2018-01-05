package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.Shot;

public class OpponentMissedResponse implements Response {

  private Shot shot;

  @JsonCreator
  public OpponentMissedResponse(@JsonProperty("shot") Shot shot) {
    this.shot = shot;
  }

  private final String HEADER = "OPPMISSED"; // TODO: check JSON

  @JsonProperty("header")
  @Override
  public String getHeader() {
    return HEADER;
  }
}
