package model;

import gui.fields.ClickableField;

import java.util.List;

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
    List<ClickableField> availablePositions = PossiblePositions.findPositions(
        shipThatIsBuild.lastMast(), sea);
    PossiblePositions.makePositionClickable(availablePositions);
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
