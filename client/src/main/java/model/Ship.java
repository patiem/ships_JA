package model;

import gui.fields.Field;
import gui.fields.Mast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class Ship implements Iterable<Mast>{

  private static final int START_SIZE = 1;

  private final Mast[] masts;
  private final int shipLength;
  private int buildLength;
  private final List<Integer> positions = new ArrayList<>();

  Ship(Mast mast, int shipLength) {
    this.shipLength = shipLength;
    masts = new Mast[this.shipLength];
    masts[0] = mast;
    positions.add(mast.positionAsInteger());
    buildLength = START_SIZE;
  }

  boolean isShipDone() {
    return buildLength == shipLength;
  }

  void addMast(Mast mast) {
    if (!isShipDone()) {
      masts[buildLength++] = mast;
      positions.add(mast.positionAsInteger());
    }
  }

  List<Position> mastsInShipPositions() {
    return Arrays.stream(masts).map(Field::position).collect(Collectors.toList());
  }

  public List<Integer> getPositions() {
    return positions;
  }

  @Override
  public Iterator<Mast> iterator() {
    return Arrays.asList(masts).iterator();
  }
}
