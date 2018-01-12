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

  private static final int START_SIZE = 1;

  private final List<Field> masts;
  private final int shipLength;
  private int buildLength;

  Ship(Field mast, int length) {
    buildLength = START_SIZE;
    shipLength = length;
    masts = new ArrayList<>();
    masts.add(mast);
  }

  boolean isShipDone() {
    return buildLength == shipLength;
  }

  void addMast(Field mast) {
    if (!isShipDone()) {
      masts.add(mast);
      buildLength++;
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
}
