package model;

import common.model.ShipModel;

import java.util.List;

/**
 * It maps ships to their model.
 *
 * @version 1.5
 */
class ShipMapper {
  private ShipMapper() {
  }

  static ShipModel mapToModel(Ship ship) {
    List<Integer> positions = ship.positionsOfAllMastInShipAsIntegers();

    return new ShipModel(positions);
  }
}
