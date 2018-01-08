package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ResponseHeader {
  @JsonProperty("con")
  CON,
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
}
