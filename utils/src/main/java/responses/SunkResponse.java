package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import common.model.ShipModel;

import java.util.Optional;

public class SunkResponse implements Response {
  private ResponseHeader header = ResponseHeader.SUNK;

  private ShipModel shipModel;

  @JsonCreator
  public SunkResponse(@JsonProperty("sunkShip") ShipModel shipModel) {
    this.shipModel = shipModel;
  }

  @Override
  @JsonProperty("sunkShip")
  public Optional<ShipModel> getSunkShip() {
    return Optional.of(shipModel);
  }

  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
