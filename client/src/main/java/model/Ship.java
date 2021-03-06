package model;

import gui.fields.Field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * It holds information on a particular ship.
 *
 * @version 1.5
 */
public class Ship implements Iterable<Field> {

  private final List<Field> masts;
  private final int shipLength;

  public Ship(int length) {
    shipLength = length;
    masts = new ArrayList<>();
  }

  boolean isShipDone() {
    return masts.size() == shipLength;
  }

  void addMast(Field field) {
    masts.add(field);
  }

  List<Position> positionsOfAllMastInShip() {
    return masts.stream().map(Field::position).collect(Collectors.toList());
  }

  public List<Field> getMasts() {
    return masts;
  }

  List<Integer> positionsOfAllMastInShipAsIntegers() {
    return masts.stream().map(Field::positionAsInteger).collect(Collectors.toList());
  }

  Field lastMast() {
    return masts.get(masts.size() - 1);
  }

  @Override
  public Iterator<Field> iterator() {
    return masts.iterator();
  }
}
