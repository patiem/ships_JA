package model;

import gui.fields.Mast;

import java.util.List;
/**
 * It holds information regarding a player's fleet.
 *
 * @author Patrycja Mikulska
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

  public void startToBuildOneShip(Mast mast, int shipLength) {
    Ship ship = new Ship(shipLength);
    shipThatIsBuild = ship;
    fleet.addShip(ship);
    addMastToShip(mast);
  }

  public void addMastToShip(Mast mast) {
    shipThatIsBuild.addMast(mast);
    if (shipThatIsBuild.isShipDone()) {
      updateBoard();
    } else {
      updateNeighbourFields(mast);
    }
  }

  private void updateBoard() {
    Updater updater = new ShipBoardUpdater(sea, shipThatIsBuild);
    updater.update();
  }

  private void updateNeighbourFields(Mast mast) {
    PossiblePositions possible = new PossiblePositions();
    possible.findPositions(mast, sea).makePositionClickable();
  }

  public List<Position> getMastsPositions() {
    return fleet.mastsPositions();
  }

  public Fleet fleet() {
    return fleet;
  }
}
