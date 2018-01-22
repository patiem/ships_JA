package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ResponseHeader {
  @JsonProperty("none")
  NONE,
  @JsonProperty("hit")
  HIT,
  @JsonProperty("lost")
  LOST,
  @JsonProperty("missed")
  MISSED,
  @JsonProperty("opphit")
  OPPHIT,
  @JsonProperty("oppmissed")
  OPPMISSED,
  @JsonProperty("play")
  PLAY,
  @JsonProperty("win")
  WIN,
  @JsonProperty("sunk")
  SUNK,
  @JsonProperty("hit_again")
  HIT_AGAIN
}
