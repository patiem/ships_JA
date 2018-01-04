package model;

import gui.fields.Mast;

import java.util.HashSet;
import java.util.Set;

public class ShipBoundariesPositions {

  private Set<Position> boundaries;

  public ShipBoundariesPositions() {
    this.boundaries = new HashSet<>();
  }

  public ShipBoundariesPositions calculateShipBoundariesPositions(Ship ship) {
    for (Mast mast : ship) {
      boundaries.addAll(new MastBoundariesPositions(mast).countBoundariesForMast());
    }
    boundaries.removeAll(ship.mastsInShipPositions());
    return this;
  }

  public void markSeaAsBoundary(Sea sea) {
    boundaries.forEach(m -> sea.makeBoundary(m));
  }
}
