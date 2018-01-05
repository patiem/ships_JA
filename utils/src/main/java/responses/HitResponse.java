package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitResponse implements Response {
  private final String HEADER = "HIT";


  @JsonProperty("header")
  @Override
  public String getHeader() {
    return HEADER;
  }
}
