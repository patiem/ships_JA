package model;

import gui.fields.Field;

public class DummyMast implements Field {

  private int column;
  private int row;

  DummyMast(int position) {
    int boardSize = 10;
    this.column = column % boardSize;
    this.row = position / boardSize;
  }

  @Override
  public int getColumn() {
    return column;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public void markAsHit() {

  }
}
