package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConnectionResponse implements Response {

  private ResponseHeader header = ResponseHeader.CON;
  private List<Integer> fleetPositions;

  @JsonCreator
  public ConnectionResponse(@JsonProperty("fleetPositions") List<Integer> fleetPositions) {
    this.fleetPositions = fleetPositions;
  }

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }

  @JsonProperty("fleetPositions")
  public List<Integer> getFleetPositions() {
    return fleetPositions;
  }
}
