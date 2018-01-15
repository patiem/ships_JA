package model;

import gui.fields.Field;

import java.util.List;
/**
 * It holds information regarding a player's fleet.
 *
 * @version 1.5
 */

public class FleetCreator {

  private final Sea sea;
  private final Fleet fleet;
  private Ship shipThatIsBuild;

  public FleetCreator(Sea sea) {
    this.sea = sea;
    fleet = new Fleet();
  }

  public void startToBuildOneShip(Field mast, int shipLength) {
    Ship ship = new Ship(shipLength);
    shipThatIsBuild = ship;
    fleet.addShip(ship);
    addMastToShip(mast);
  }

  public void addMastToShip(Field mast) {
    shipThatIsBuild.addMast(mast);
    Updater updater = new ShipBoardUpdater(sea, shipThatIsBuild);
    updater.update(shipThatIsBuild.isShipDone());
  }

  public List<Position> getMastsPositions() {
    return fleet.mastsPositions();
  }

  public Fleet fleet() {
    return fleet;
  }
}
