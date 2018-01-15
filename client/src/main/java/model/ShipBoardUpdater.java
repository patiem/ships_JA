package model;

public class ShipBoardUpdater implements Updater {
  private Sea sea;
  private Ship shipThatIsBuild;

  public ShipBoardUpdater(Sea sea, Ship shipThatIsBuild) {
    this.sea = sea;
    this.shipThatIsBuild = shipThatIsBuild;
  }

  @Override
  public void update(Boolean isShipDone) {
    if (isShipDone) {
      resetSeaFields();
      makeBoundaries();
    } else {
      makeClickableNeighbours();
    }
  }

  private void makeClickableNeighbours() {
    PossiblePositions possible = new PossiblePositions();
    possible.findPositions(shipThatIsBuild.lastMast(), sea).makePositionClickable();
  }

  private void resetSeaFields() {
    sea.clearSea();
  }

  private void makeBoundaries() {
    ShipBoundariesPositions boundaries = new ShipBoundariesPositions();
    boundaries.calculateShipBoundariesPositions(shipThatIsBuild);
    boundaries.markSeaAsBoundary(sea);
  }
}
