package model;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

  private List<Ship> ships;

  public Fleet() {
    this.ships = new ArrayList<>();
  }

  public void addShip(Ship ship) {
    ships.add(ship);
  }

  public List<Ship> getShips() {
    return ships;
  }

  public List<Position> countMastPositions() {
    List<Position> positions = new ArrayList<>();
    ships.stream()
        .map(Ship::positionsOfAllMastInShip)
        .forEach(positions::addAll);
    return positions;
  }
}
