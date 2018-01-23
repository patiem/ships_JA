package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import common.model.Shot;

/**
 * It represents the message containing information about shot.
 *
 * @author Emilia Ciastek
 * @version 1.5
 */
public class ShotMessage {
  private final Shot shot;

  @JsonCreator
  public ShotMessage(@JsonProperty("shot") Shot shot) {
    this.shot = shot;
  }

  @JsonProperty("shot")
  public Shot getShot() {
    return shot;
  }
}
