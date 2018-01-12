package model;

public class ShipBoardUpdater implements Updater {
  private Sea sea;
  private Ship shipThatIsBuild;

  public ShipBoardUpdater(Sea sea, Ship shipThatIsBuild) {
    this.sea = sea;
    this.shipThatIsBuild = shipThatIsBuild;
  }

  @Override
  public void update() {
    sea.clearSea();
    ShipBoundariesPositions boundaries = new ShipBoundariesPositions();
    boundaries.calculateShipBoundariesPositions(shipThatIsBuild);
    boundaries.markSeaAsBoundary(sea);
  }
}
