package common.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * It stores information on each shot.
 *
 * @version 1.5
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Shot {

  private final Integer shotPosition;

  @JsonCreator
  public Shot(@JsonProperty("position") Integer shotPosition) {
    this.shotPosition = shotPosition;
  }

  @JsonProperty("position")
  public Integer asInteger() {
    return shotPosition;
  }

  @Override
  public String toString() {
    return String.valueOf(shotPosition);
  }
}
