package model;

import java.util.ArrayList;
import java.util.List;

class ShipMapper {

  ShipModel mapToModel(Ship ship) {
    List<Integer> positions = new ArrayList<>(ship.getPositions());

    return new ShipModel(positions);
  }
}
