package model;

import gui.fields.ClickableField;
import gui.fields.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * It holds information on the fields where a mast can be placed.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
class PossiblePositions {

  private PossiblePositions() {
  }

  static List<ClickableField> findPositions(Field mast, Sea sea) {
    List<ClickableField> possible = new ArrayList<>();
    final int leftBoundary = 1;
    final int upperBoundary = 1;
    final int rightBoundary = 8;
    final int bottomBoundary = 8;
    if (mast.getRow() >= leftBoundary) {
      possible.add(sea.getSeaFieldByPosition(Position.up(mast.position())));
    }
    if (mast.getRow() <= rightBoundary) {
      possible.add(sea.getSeaFieldByPosition(Position.down(mast.position())));
    }
    if (mast.getColumn() <= bottomBoundary) {
      possible.add(sea.getSeaFieldByPosition(Position.right(mast.position())));
    }
    if (mast.getColumn() >= upperBoundary) {
      possible.add(sea.getSeaFieldByPosition(Position.left(mast.position())));
    }
    return possible;
  }

  static void makePositionClickable(List<ClickableField> possibleFields) {
    possibleFields.forEach(ClickableField::makeClickable);
  }
}
