package model;

import gui.fields.ClickableField;

import java.util.List;

/**
 * Updates board after users click, action depends on if ship is done or is still randomize.
 *
 * @version 1.5
 */
public class ShipBoardUpdater {
  private PossiblePositions possiblePositions;
  private ShipBoundariesPositions boundariesPositions;
  private SeaCleaner seaCleaner;
  private Ship lastShip;

  public ShipBoardUpdater(PossiblePositions possiblePositions,
                          ShipBoundariesPositions boundariesPositions,
                          SeaCleaner seaCleaner) {
    this.possiblePositions = possiblePositions;
    this.boundariesPositions = boundariesPositions;
    this.seaCleaner = seaCleaner;
  }

  /**
   * Updates board. Action depends on if ship is done or is still randomize.
   * If ship is done reset fields and creates boundaries around ship.
   * If ship is still in progress creates fields that can be clicked.
   *
   */
  public void update(Ship ship) {
    lastShip = ship;
    update();
  }

  public void update() {
    if (lastShip.isShipDone()) {
      resetSeaFields();
      makeBoundaries(lastShip);
    } else {
      makeClickableNeighbours();
    }
  }

  private void makeClickableNeighbours() {
    List<ClickableField> posibble = possiblePositions.findPositions(lastShip.lastMast());
    possiblePositions.makePositionClickable(posibble);
  }

  private void resetSeaFields() {
    seaCleaner.clean();
  }

  private void makeBoundaries(Ship ship) {
    boundariesPositions.calculateShipBoundariesPositions(ship);
    boundariesPositions.markSeaAsBoundary();
  }
}
