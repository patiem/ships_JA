package model;

import java.util.Objects;

public class Position {

  private final Integer column;
  private final Integer row;

  public Position(int posX, int posY) {
    this.column = posX;
    this.row = posY;
  }

  static Position up(Position position) {
    return new Position(position.getColumn(), position.getRow() - 1);
  }

  static Position down(Position position) {
    return new Position(position.getColumn(), position.getRow() + 1);
  }

  static Position right(Position position) {
    return new Position(position.getColumn() + 1, position.getRow());
  }

  static Position left(Position position) {
    return new Position(position.getColumn() - 1, position.getRow());
  }

  public Integer getColumn() {
    return column;
  }

  public Integer getRow() {
    return row;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return Objects.equals(column, position.column) &&
        Objects.equals(row, position.row);
  }

  @Override
  public int hashCode() {
    return Objects.hash(column, row);
  }

  @Override
  public String toString() {
    return "Position{" +
        "column=" + column +
        ", row=" + row +
        '}';
  }
}
