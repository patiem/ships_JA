package gui.fields;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.FieldSize;

/**
 * It holds information on and state of every field of the board.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class SeaField extends Rectangle implements Field {

  private final Integer column;
  private final Integer row;
  private final SimpleBooleanProperty isMarkedAsMast;
  private final SimpleBooleanProperty isMarkedAsBound;
  public final EventHandler<MouseEvent> makeReadyToClick = event -> setIsMarkedAsMast(true);

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

  public void reset() {
    setFill(Color.AZURE);
    setStroke(Color.GRAY);
  }

  public void marked() {
    setFill(Color.GREEN);
    setStroke(Color.GRAY);
  }

  public void missed() {
    setFill(Color.BLACK);
  }

  public void makeToClick() {
    setFill(Color.BLANCHEDALMOND);
    this.addEventHandler(MouseEvent.MOUSE_CLICKED, makeReadyToClick);
  }

  private void setIsMarkedAsMast(boolean isMarkedAsMast) {
    this.isMarkedAsMast.set(isMarkedAsMast);
  }

  public SimpleBooleanProperty isMarkedAsMastProperty() {
    return isMarkedAsMast;
  }

  public SimpleBooleanProperty isMarkedAsBoundProperty() {
    return isMarkedAsBound;
  }

  public void setIsMarkedAsBound(boolean isMarkedAsBound) {
    this.isMarkedAsBound.set(isMarkedAsBound);
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
    setFill(Color.DARKBLUE);
  }
}