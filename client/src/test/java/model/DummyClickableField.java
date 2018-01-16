package model;


import gui.fields.ClickableField;

class DummyClickableField implements ClickableField {

  private final Integer row;
  private final Integer col;

  DummyClickableField(Integer row, Integer col) {
    this.row = row;
    this.col = col;
  }

  DummyClickableField(int position) {
    int boardSize = 10;
    this.col = position % boardSize;
    this.row = position / boardSize;
  }

  @Override
  public void makeUnclickable() {
  }

  @Override
  public void makeClickable() {
  }

  @Override
  public void markAsBound(boolean isMarkedAsBound) {
  }

  @Override
  public int getColumn() {
    return col;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public void markAsHit() {

  }
}
