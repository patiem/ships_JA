package gui.fields;

import model.Position;

/**
 * It exposes methods for obtaining information on field position.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public interface Field {

  int getColumn();

  int getRow();

  default Position position() {
    return new Position(getColumn(), getRow());
  }

  default Integer positionAsInteger() {
    int numberOfColumns = 10;
    return getColumn() + getRow() * numberOfColumns;
  }

  void markAsHit();
}
