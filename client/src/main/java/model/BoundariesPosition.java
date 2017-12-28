package model;

import java.util.ArrayList;
import java.util.List;

public class BoundariesPosition {

  private Mast mast;

  public BoundariesPosition(Mast mast) {
    this.mast = mast;
  }

  public List<Position> countBoundariesForMast() {

    int minColumn = 0;
    int minRow = 0;
    int maxColumn = 9;
    int maxRow = 9;

    List<Position> boundaries = new ArrayList<>();

    for (int neighbourColumnValue = -1; neighbourColumnValue <= 1; neighbourColumnValue++) {
      for (int neighbourRowValue = -1; neighbourRowValue <= 1; neighbourRowValue++) {
        int newPositionX = mast.getColumn() + neighbourColumnValue;
        int newPositionY = mast.getRow() + neighbourRowValue;
        if (newPositionX >= minColumn && newPositionX <= maxColumn
            && newPositionY >= minRow && newPositionY <= maxRow) {
          boundaries.add(new Position(newPositionX, newPositionY));
        }
      }
    }
    return boundaries;
  }
}
