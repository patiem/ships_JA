package model;

import gui.fields.SeaField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * It represents the entire board where ships are deployed.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
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
    wholeSea.forEach(SeaField::makeUnclickable);
    wholeSea.forEach(SeaField::resetColors);
  }

  public void makeBoundary(Position position) {
    getSeaFieldByPosition(position).setIsMarkedAsBound(true);
  }
}
