package model;

import gui.fields.ClickableField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * It represents the entire board where ships are deployed.
 *
 * @version 1.5
 */
public class Sea {

  private final List<ClickableField> seaFields;

  public Sea() {
    this.seaFields = new ArrayList<>();
  }

  public void addSeaField(ClickableField seaField) {
    seaFields.add(seaField);
  }

  ClickableField getSeaFieldByPosition(Position position) {
    Optional<ClickableField> field = seaFields
        .stream()
        .filter(s -> s.position().equals(position))
        .findFirst();

    return field.orElseThrow(IndexOutOfBoundsException::new);
  }

  void clearSea() {
    seaFields.forEach(ClickableField::makeUnclickable);
  }

  void makeBoundary(Position position) {
    getSeaFieldByPosition(position).markAsBound(true);
  }
}
