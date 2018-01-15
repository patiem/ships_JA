package model;

/**
 * Updates board after users click, action depends on if ship is done or is still build.
 *
 * @version 1.5
 */
public class ShipBoardUpdater {
  private Sea sea;
  private Ship ship;

  public ShipBoardUpdater(Sea sea, Ship ship) {
    this.sea = sea;
    this.ship = ship;
  }

  /**
   * Updates board. Action depends on if ship is done or is still build.
   * If ship is done reset fields and creates boundaries around ship.
   * If ship is still in progress creates fields that can be clicked.
   *
   */
  public void update() {
    if (ship.isShipDone()) {
      resetSeaFields();
      makeBoundaries();
    } else {
      makeClickableNeighbours();
    }
  }

  private void makeClickableNeighbours() {
    PossiblePositions possible = new PossiblePositions();
    possible.findPositions(ship.lastMast(), sea).makePositionClickable();
  }

  private void resetSeaFields() {
    sea.clearSea();
  }

  private void makeBoundaries() {
    ShipBoundariesPositions boundaries = new ShipBoundariesPositions();
    boundaries.calculateShipBoundariesPositions(ship);
    boundaries.markSeaAsBoundary(sea);
  }
}
