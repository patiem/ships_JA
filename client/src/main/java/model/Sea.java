package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.input.MouseEvent;

public class Sea {

  private List<SeaField> wholeSea;

  public Sea() {
    this.wholeSea = new ArrayList<>();
  }

  public void addSeaField(SeaField seaField) {
    wholeSea.add(seaField);
  }

  SeaField getSeaFieldByPosition(Position position) {

    Optional<SeaField> field = wholeSea.stream()
        .filter(s -> s.position().equals(position))
        .findFirst();

    return field.orElseThrow(IndexOutOfBoundsException::new);
  }

  public void clearSea() {
    wholeSea.forEach(s -> s.removeEventHandler(MouseEvent.MOUSE_CLICKED, s.makeReadyToClick));
    wholeSea.forEach(SeaField::reset);
  }

  public void makeBoundaries(Ship lastShip) {
    lastShip.calculateShipBoundariesPositions()
        .forEach(m -> getSeaFieldByPosition(m).setIsMarkedAsBound(true));
  }
}
