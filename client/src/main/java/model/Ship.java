package model;

import gui.fields.Field;
import gui.fields.Mast;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * It holds information on a particular ship.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
class Ship implements Iterable<Mast>{

  private static final int START_SIZE = 1;

  private final Mast[] masts;
  private final int shipLength;
  private int buildLength;

  Ship(Mast mast, int length) {
    shipLength = length;
    masts = new Mast[shipLength];
    masts[0] = mast;
    buildLength = START_SIZE;
  }

  boolean isShipDone() {
    return buildLength == shipLength;
  }

  void addMast(Mast mast) {
    if (!isShipDone()) {
      masts[buildLength++] = mast;
    }
  }

  List<Position> positionsOfAllMastInShip() {
    return Arrays.stream(masts).map(Field::position).collect(Collectors.toList());
  }

  List<Integer> positionsOfAllMastInShipAsIntegers() {
    return Arrays.stream(masts).map(Field::positionAsInteger).collect(Collectors.toList());
  }

  @Override
  public Iterator<Mast> iterator() {
    return Arrays.asList(masts).iterator();
  }
}
