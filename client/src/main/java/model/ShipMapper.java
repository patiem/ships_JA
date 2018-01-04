package model;

import java.util.ArrayList;
import java.util.List;

/**
 * It maps ships to their model.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
class ShipMapper {

  ShipModel mapToModel(Ship ship) {
    List<Integer> positions = new ArrayList<>(ship.getPositions());

    return new ShipModel(positions);
  }
}
