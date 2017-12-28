package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ship {

  private static final int START_SIZE = 1;

  private final Mast[] masts;
  private final int shipLength;
  private int buildLength;
  private List<Integer> positions = new ArrayList<>();

  public Ship(Mast mast, int shipLength) {
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

  public List<Position> possibleMastsPositions() {
    return Arrays.stream(masts).map(m -> m.position()).collect(Collectors.toList());
  }

  public Set<Position> calculateShipBoundariesPositions() {
    Set<Position> boundaries = new HashSet<>();
    for (Mast mast : masts) {
      boundaries.addAll(new BoundariesPosition(mast).countBoundariesForMast());
    }
    boundaries.removeAll(possibleMastsPositions());
    return boundaries;
  }

  public List<Integer> getPositions() {
    return positions;
  }
}
