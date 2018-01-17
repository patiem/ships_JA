package model;

import java.util.ArrayList;
import java.util.List;
/**
 * It calculates which fields are not available for ship deployment.
 *
 * @version 1.5
 */
class MastBoundariesPositions {

  private final Position position;

  MastBoundariesPositions(Position position) {
    this.position = position;
  }

  public List<Position> countBoundariesForMast() {

    int minColumn = 0;
    int minRow = 0;
    int maxColumn = 9;
    int maxRow = 9;
    int step = 1;

    List<Position> boundaries = new ArrayList<>();

    for (int neighbourColumnValue = -step; neighbourColumnValue <= step; neighbourColumnValue++) {
      for (int neighbourRowValue = -step; neighbourRowValue <= step; neighbourRowValue++) {
        int newPositionX = position.getColumn() + neighbourColumnValue;
        int newPositionY = position.getRow() + neighbourRowValue;
        if (newPositionX >= minColumn && newPositionX <= maxColumn
            && newPositionY >= minRow && newPositionY <= maxRow) {
          boundaries.add(new Position(newPositionX, newPositionY));
        }
      }
    }
    return boundaries;
  }
}
