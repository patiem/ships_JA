package model;

import gui.fields.Field;

import java.util.ArrayList;
import java.util.List;
/**
 * It calculates which fields are not available for ship deployment.
 *
 * @version 1.5
 */
class MastBoundariesPositions {

  private final Field field;

  MastBoundariesPositions(Field field) {
    this.field = field;
  }

  public List<Position> countBoundariesForMast() {

    int minColumn = 0;
    int minRow = 0;
    int maxColumn = 9;
    int maxRow = 9;

    List<Position> boundaries = new ArrayList<>();

    for (int neighbourColumnValue = -1; neighbourColumnValue <= 1; neighbourColumnValue++) {
      for (int neighbourRowValue = -1; neighbourRowValue <= 1; neighbourRowValue++) {
        int newPositionX = field.getColumn() + neighbourColumnValue;
        int newPositionY = field.getRow() + neighbourRowValue;
        if (newPositionX >= minColumn && newPositionX <= maxColumn
            && newPositionY >= minRow && newPositionY <= maxRow) {
          boundaries.add(new Position(newPositionX, newPositionY));
        }
      }
    }
    return boundaries;
  }
}
