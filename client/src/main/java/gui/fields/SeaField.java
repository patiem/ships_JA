package gui.fields;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * It holds information on and state of every field of the board.
 *
 * @version 1.5
 */
public class SeaField extends Rectangle implements ClickableField {

  private final Integer column;
  private final Integer row;
  private final SimpleBooleanProperty isMarkedAsMast;
  private final SimpleBooleanProperty isMarkedAsBound;

  private final EventHandler<MouseEvent> makeReadyToClick = event -> markAsMast(true);

  public SeaField(int column, int row, FieldSize size) {
    super(column, row, size.getValue(), size.getValue());
    this.column = column;
    this.row = row;
    setFill(Color.AZURE);
    setStroke(Color.GRAY);
    isMarkedAsMast = new SimpleBooleanProperty(this, "mast", false);
    isMarkedAsBound = new SimpleBooleanProperty(this, "bound", false);
  }

  public void hit() {
    setFill(Color.RED);
  }

  public void marked() {
    setStroke(Color.GRAY);
  }

  public void missed() {
    setFill(Color.BLUE);
  }

  @Override
  public void makeClickable() {
    setFill(Color.BLANCHEDALMOND);
    this.addEventHandler(MouseEvent.MOUSE_CLICKED, makeReadyToClick);
  }

  @Override
  public void makeUnclickable() {
    removeEventHandler(MouseEvent.MOUSE_CLICKED, makeReadyToClick);
    resetColors();
  }

  public void resetColors() {
    setFill(Color.AZURE);
    setStroke(Color.GRAY);
  }

  @Override
  public void markAsBound(boolean isMarkedAsBound) {
    this.isMarkedAsBound.set(isMarkedAsBound);
  }

  private void markAsMast(boolean isMarkedAsMast) {
    this.isMarkedAsMast.set(isMarkedAsMast);
  }

  public SimpleBooleanProperty isMarkedAsMastProperty() {
    return isMarkedAsMast;
  }

  public SimpleBooleanProperty isMarkedAsBoundProperty() {
    return isMarkedAsBound;
  }

  public void markAsHit() {
    setFill(Color.BLUE);
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
