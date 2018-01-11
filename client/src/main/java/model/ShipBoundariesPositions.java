package model;

import gui.fields.Mast;

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
    for (Mast mast : ship) {
      boundaries.addAll(new MastBoundariesPositions(mast).countBoundariesForMast());
    }
    boundaries.removeAll(ship.positionsOfAllMastInShip());
    return this;
  }

  public void markSeaAsBoundary(Sea sea) {
    boundaries.forEach(sea::makeBoundary);
  }
}
