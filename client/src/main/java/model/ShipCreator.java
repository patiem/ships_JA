package model;

import gui.fields.Field;

public class ShipCreator {

  private Ship ship;

  public ShipCreator(Ship ship) {
    this.ship = ship;
  }

  public void addMastToShip(Field mast, Sea sea) {
    ship.addMast(mast);
    ShipBoardUpdater updater = new ShipBoardUpdater(sea, ship);
    updater.update();
  }
}
