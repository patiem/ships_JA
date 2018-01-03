package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * It represents the fleet.
 * @author Emilia Ciastek
 * @version 1.5
 */
public class FleetModel {

  private final List<ShipModel> ships = new ArrayList<>();

  @JsonCreator
  public FleetModel(@JsonProperty("ships") List<ShipModel> ships) {
    this.ships.addAll(ships);
  }

  @JsonProperty("ships")
  public List<ShipModel> getShips() {
    return ships;
  }

}
