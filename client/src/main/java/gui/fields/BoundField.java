package gui.fields;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.FieldSize;

/**
 * It represents the fields that are not available for ship deployment.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class BoundField extends Rectangle implements Field {

  private final Integer column;
  private final Integer row;

  public BoundField(int column, int row, FieldSize size) {
    super(column, row, size.getValue(), size.getValue());
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

  @Override
  public void markAsHit() {
    setFill(Color.GRAY);
  }
}
