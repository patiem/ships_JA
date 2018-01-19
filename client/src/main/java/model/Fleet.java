package model;

import gui.fields.Field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Fleet implements Iterable<Ship> {

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

  public List<Field> getFields(){
    return ships.stream()
        .map(Ship::getMasts)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  public boolean isFleetEmpty() {
    return ships.isEmpty();
  }

  @Override
  public Iterator<Ship> iterator() {
    return ships.iterator();
  }
}
