package gui.fields;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * It represents a mast of a ship.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class Mast extends Rectangle implements Field {

  private final Integer column;
  private final Integer row;

  public Mast(int column, int row) {
    super(column, row, SIZE, SIZE);
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
}
