package model;

import gui.fields.SeaField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Sea {

  private final List<SeaField> wholeSea;

  public Sea() {
    this.wholeSea = new ArrayList<>();
  }

  public void addSeaField(SeaField seaField) {
    wholeSea.add(seaField);
  }

  public SeaField getSeaFieldByPosition(Position position) {

    Optional<SeaField> field = wholeSea.stream()
        .filter(s -> s.position().equals(position))
        .findFirst();

    return field.orElseThrow(IndexOutOfBoundsException::new);
  }

  public void clearSea() {
    wholeSea.forEach(s -> s.removeEventHandler(MouseEvent.MOUSE_CLICKED, s.makeReadyToClick));
    wholeSea.forEach(SeaField::reset);
  }

  public void makeBoundary(Position position) {
    getSeaFieldByPosition(position).setIsMarkedAsBound(true);
  }
}
