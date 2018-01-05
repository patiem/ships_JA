package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LossResponse implements Response {

  private final String HEADER = "OPPMISSED"; // TODO: check JSON

  @JsonProperty("header")
  @Override
  public String getHeader() {
    return HEADER;
  }
}
