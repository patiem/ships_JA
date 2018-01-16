package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.ShipModel;
import model.Shot;

public class SunkResponse implements Response {
  private ResponseHeader header = ResponseHeader.SUNK;

  ShipModel shipModel;


  @JsonCreator
  public SunkResponse(@JsonProperty("ship") Shot shot) {
    this.shot = shot;
  }




  @JsonProperty("header")
  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}