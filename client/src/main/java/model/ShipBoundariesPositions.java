package model;

import gui.fields.Field;

import java.util.HashSet;
import java.util.Set;

/**
 * It marks the fields around a ship that are not available for ship deployment.
 *
 * @version 1.5
 */
public class ShipBoundariesPositions {

  private Set<Position> boundaries;
  private Sea sea;

  public ShipBoundariesPositions(Sea sea) {
    this.boundaries = new HashSet<>();
    this.sea = sea;
  }

  public ShipBoundariesPositions calculateShipBoundariesPositions(Ship ship) {

    for (Field mast : ship) {
      MastBoundariesPositions mastBoundaries = new MastBoundariesPositions(mast);
      boundaries.addAll(mastBoundaries.countBoundariesForMast());
    }
    boundaries.removeAll(ship.positionsOfAllMastInShip());
    return this;
  }

  public void markSeaAsBoundary() {
    boundaries.forEach(sea::makeBoundary);
  }
}
