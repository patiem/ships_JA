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

  private List<ClickableField> possible;

  public PossiblePositions() {
    possible = new ArrayList<>();
  }

  public PossiblePositions findPositions(Field field, Sea sea) {
    final int leftBoundary = 1;
    final int upperBoundary = 1;
    final int rightBoundary = 8;
    final int bottomBoundary = 8;
    if (field.getRow() >= leftBoundary) {
      possible.add(sea.getSeaFieldByPosition(Position.up(field.position())));
    }
    if (field.getRow() <= rightBoundary) {
      possible.add(sea.getSeaFieldByPosition(Position.down(field.position())));
    }
    if (field.getColumn() <= bottomBoundary) {
      possible.add(sea.getSeaFieldByPosition(Position.right(field.position())));
    }
    if (field.getColumn() >= upperBoundary) {
      possible.add(sea.getSeaFieldByPosition(Position.left(field.position())));
    }
    return this;
  }

  public void makePositionClickable() {
    possible.stream().forEach(ClickableField::makeClickable);
  }
}
