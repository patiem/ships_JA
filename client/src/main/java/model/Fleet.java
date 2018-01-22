package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  public List<Position> mastsPositions() {
    return ships.stream()
        .map(Ship::positionsOfAllMastInShip)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  public boolean isFleetFullyBuilt() {
    int numberOfShipsInFleet = 10;
    return ships.size() == numberOfShipsInFleet;
  }
}
