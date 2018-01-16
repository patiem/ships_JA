package model;

import gui.fields.Field;

import java.util.HashSet;
import java.util.Set;

/**
 * It marks the fields around a ship that are not available for ship deployment.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class ShipBoundariesPositions {

  private Set<Position> boundaries;

  public ShipBoundariesPositions() {
    this.boundaries = new HashSet<>();
  }

  public ShipBoundariesPositions calculateShipBoundariesPositions(Ship ship) {

    for (Field mast : ship) {
      MastBoundariesPositions mastBoundaries = new MastBoundariesPositions(mast);
      boundaries.addAll(mastBoundaries.countBoundariesForMast());
    }
    boundaries.removeAll(ship.positionsOfAllMastInShip());
    return this;
  }

  public void markSeaAsBoundary(Sea sea) {
    boundaries.forEach(sea::makeBoundary);
  }
}
