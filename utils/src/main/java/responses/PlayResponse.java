package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayResponse implements Response{

  private final String HEADER = "PLAY"; // TODO: check JSON

  @JsonProperty("header")
  @Override
  public String getHeader() {
    return HEADER;
  }
}
