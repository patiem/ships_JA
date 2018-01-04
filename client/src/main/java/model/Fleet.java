package model;

import gui.fields.Mast;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

//  private final List<Mast> masts;
  private List<Position> positions;
  private final Sea sea;
  private final List<Ship> ships;
  private Ship shipThatIsBuild;

  public Fleet(Sea sea) {
    this.sea = sea;
    positions = new ArrayList<>();
    ships = new ArrayList<>();
  }

//  private void addFirstMastToFleet(Mast mast) {
//    masts.add(mast);
//  }

  public void startToBuildOneShip(Mast mast, int shipLength) {
//    addFirstMastToFleet(mast);
    Ship ship = new Ship(mast, shipLength);
    shipThatIsBuild = ship;
    ships.add(ship);
    positions.add(mast.position());
    updateNeighbourFields(mast);
  }

  private void updateNeighbourFields(Mast mast) {
    if (shipThatIsBuild.isShipDone()) {
      sea.clearSea();
      ShipBoundariesPositions boundries = new ShipBoundariesPositions();
      boundries.calculateShipBoundariesPositions(shipThatIsBuild);
      boundries.markSeaAsBoundary(sea);
      return;
    }
    PossiblePositions possible = new PossiblePositions();
    possible.findPositions(mast, sea).makePositionClickable();
  }

  public void addNextMastToShip(Mast mast) {
    shipThatIsBuild.addMast(mast);
    updateNeighbourFields(mast);
    positions.add(mast.position());
  }

  public List<Ship> getShips() {
    return ships;
  }

  public List<Position> getMastsPositions() {
    return positions;
  }
}
