package model;

import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    return field.get();
  }

  public void clearSea() {
    wholeSea.stream()
        .forEach(s -> s.removeEventHandler(MouseEvent.MOUSE_CLICKED, s.makeReadyToClick));
    wholeSea.stream()
        .forEach(s -> s.reset());
  }

  public void makeBoundaries(Ship lastShip) {
    lastShip.calculateShipBoundariesPositions()
        .stream()
        .forEach(m -> getSeaFieldByPosition(m).setIsMarkedAsBound(true));
  }
}
