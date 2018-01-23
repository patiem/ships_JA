package model;

import gui.fields.Field;

/**
 * It maps the received fleet to a fleet model.
 *
 * @version 1.5
 */
public class ShipCreator {

  private Ship ship;

  public ShipCreator(Ship ship) {
    this.ship = ship;
  }

  public void addMastToShip(Field mast) {
    if (!ship.isShipDone()) {
      ship.addMast(mast);
    } else {
      throw new IllegalStateException("Ship is already randomize");
    }
  }

}
