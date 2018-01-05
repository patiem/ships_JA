package gui.fields;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.FieldSize;

/**
 * It represents a mast of a ship.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class Mast extends Rectangle implements Field {

  private final Integer column;
  private final Integer row;

  public Mast(int column, int row, FieldSize size) {
    super(column, row, size.getValue(), size.getValue());
    this.column = column;
    this.row = row;
    setFill(Color.FUCHSIA);
    setStroke(Color.GREEN);
  }

  @Override
  public int getColumn() {
    return column;
  }

  @Override
  public int getRow() {
    return row;
  }

  public void markedAsHit() {
    setFill(Color.BLACK);
  }
}
