package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SeaField extends Rectangle implements Field {

  private final Integer column;
  private final Integer row;
  private SimpleBooleanProperty isMarkedAsMast;
  EventHandler<MouseEvent> makeReadyToClick = event -> setIsMarkedAsMast(true);
  private SimpleBooleanProperty isMarkedAsBound;

  public SeaField(int column, int row) {
    super(column, row, SIZE, SIZE);
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

  public Integer calculateListPosition() {
    Integer rowLength = 10;
    return column + row * rowLength;
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
}