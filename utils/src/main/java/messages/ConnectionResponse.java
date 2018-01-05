package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConnectionResponse implements Message {

  private String header;
  private List<Integer> fleetPositions;

  @JsonCreator
  public ConnectionResponse(@JsonProperty("header") String header,
                            @JsonProperty("fleetPositions") List<Integer> fleetPositions) {
    this.header = header;
    this.fleetPositions = fleetPositions;
  }

  @JsonProperty("header")
  @Override
  public String getHeader() {
    return header;
  }

  @JsonProperty("fleetPositions")
  public List<Integer> getFleetPositions() {
    return fleetPositions;
  }
}
