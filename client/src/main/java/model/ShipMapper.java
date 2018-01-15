package model;

import java.util.List;

/**
 * It maps ships to their model.
 *
 * @author Emilia Ciastek
 * @version 1.5
 */
class ShipMapper {

  ShipModel mapToModel(Ship ship) {
    List<Integer> positions = ship.positionsOfAllMastInShipAsIntegers();

    return new ShipModel(positions);
  }
}
