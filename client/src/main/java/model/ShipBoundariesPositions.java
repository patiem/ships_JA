package model;

import gui.fields.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
      boundariesForMast(mast.position());
    }
    boundaries.removeAll(ship.positionsOfAllMastInShip());
    return this;
  }

  public ShipBoundariesPositions calculateShipBoundariesPositions(List<Integer> mastsPositionIndex) {
    List<Position> shipPositions = new ArrayList<>();
    for (Integer index: mastsPositionIndex) {
      Position shipPosition = new Position(index);
      boundariesForMast(shipPosition);
      shipPositions.add(shipPosition);
    }
    boundaries.removeAll(shipPositions);
    return this;
  }

  private void boundariesForMast(Position position) {
    MastBoundariesPositions mastBoundaries = new MastBoundariesPositions(position);
    boundaries.addAll(mastBoundaries.countBoundariesForMast());
  }

  public void markSeaAsBoundary() {
    boundaries.forEach(sea::makeBoundary);
  }

  public void markSunkShip() {
    boundaries.forEach(b -> sea.getSeaFieldByPosition(b).markAsHit());
  }

}
