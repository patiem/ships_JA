package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoundField extends Rectangle implements Field {

  private final Integer column;
  private final Integer row;

  public BoundField(int column, int row) {
    super(column, row, SIZE, SIZE);
    this.column = column;
    this.row = row;
    setFill(Color.DARKGREY);
    setStroke(Color.GRAY);
  }

  @Override
  public int getColumn() {
    return column;
  }

  @Override
  public int getRow() {
    return row;
  }
}
