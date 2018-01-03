package model;

interface Field {

  Integer SIZE = 30;

  int getColumn();

  int getRow();

  default Position position() {
    return new Position(getColumn(), getRow());
  }

  default Integer positionAsInteger() {
    int numberOfColumns = 10;
    return getColumn() + getRow() * numberOfColumns;
  }
}