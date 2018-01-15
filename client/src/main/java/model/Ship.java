package model;

import gui.fields.Field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * It holds information on a particular ship.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
class Ship implements Iterable<Field>{

  private final List<Field> masts;
  private final int shipLength;

  Ship(int length) {
    shipLength = length;
    masts = new ArrayList<>();
  }

  boolean isShipDone() {
    return masts.size() == shipLength;
  }

  void addMast(Field mast) {
    if (!isShipDone()) {
      masts.add(mast);
    }
  }

  List<Position> positionsOfAllMastInShip() {
    return masts.stream().map(Field::position).collect(Collectors.toList());
  }

  List<Integer> positionsOfAllMastInShipAsIntegers() {
    return masts.stream().map(Field::positionAsInteger).collect(Collectors.toList());
  }

  @Override
  public Iterator<Field> iterator() {
    return masts.iterator();
  }

  public Field lastMast() {
    return masts.stream().reduce((f,s) -> s).orElseThrow(IndexOutOfBoundsException::new);
  }
}
