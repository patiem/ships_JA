package model;

import gui.fields.ClickableField;
import gui.fields.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * It holds information on the fields where a mast can be placed.
 *
 * @version 1.5
 */
public class PossiblePositions {
  private Sea sea;

  public PossiblePositions(Sea sea) {
    this.sea = sea;
  }

  public List<ClickableField> findPositions(Field field) {
    List<ClickableField> possible = new ArrayList<>();

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
    return possible;
  }

  public void makePositionClickable(List<ClickableField> possible) {
    possible.forEach(ClickableField::makeClickable);
  }


}
