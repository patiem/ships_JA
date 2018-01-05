package responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WinResponse implements Response{

  private final String HEADER = "WIN"; // TODO: check JSON

  @JsonProperty("header")
  @Override
  public String getHeader() {
    return HEADER;
  }
}
