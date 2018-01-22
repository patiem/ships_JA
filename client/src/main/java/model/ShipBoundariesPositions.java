package model;

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

  public Set<Position> calculateShipBoundariesPositions(Ship ship) {
//    boundaries.removeAll(ship.positionsOfAllMastInShip());
    return calculateShipBoundariesPositions(ship.positionsOfAllMastInShipAsIntegers());
  }

  public Set<Position> calculateShipBoundariesPositions(List<Integer> mastsPositionIndex) {

    List<Position> shipPositions = new ArrayList<>();
    for (Integer index : mastsPositionIndex) {
      Position shipPosition = new Position(index);
      boundariesForMast(shipPosition);
      shipPositions.add(shipPosition);
    }
    boundaries.removeAll(shipPositions);
    return boundaries;
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
