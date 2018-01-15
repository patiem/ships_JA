package model;

import java.util.List;
/**
 * It holds information regarding a player's fleet.
 *
 * @version 1.5
 */

public class FleetCreator {

  private final Fleet fleet;

  public FleetCreator() {
    fleet = new Fleet();
  }

  public List<Position> getMastsPositions() {
    return fleet.mastsPositions();
  }

  public Fleet fleet() {
    return fleet;
  }

  public void addShip(Ship ship) {
    fleet.addShip(ship);
  }
}
