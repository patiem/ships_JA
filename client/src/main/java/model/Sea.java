package model;

import gui.fields.ClickableField;

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

  private final List<ClickableField> wholeSea;

  public Sea() {
    this.wholeSea = new ArrayList<>();
  }

  public void addSeaField(ClickableField seaField) {
    wholeSea.add(seaField);
  }

  public ClickableField getSeaFieldByPosition(Position position) {

    Optional<ClickableField> field = wholeSea.stream()
        .filter(s -> s.position().equals(position))
        .findFirst();

    return field.orElseThrow(IndexOutOfBoundsException::new);
  }

  public void clearSea() {
    wholeSea.forEach(ClickableField::makeUnclickable);
  }

  public void makeBoundary(Position position) {
    getSeaFieldByPosition(position).setIsMarkedAsBound(true);
  }
}
