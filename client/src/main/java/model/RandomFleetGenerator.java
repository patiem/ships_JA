package model;

import gui.fields.Field;
import gui.fields.FieldSize;
import gui.fields.Mast;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomFleetGenerator {

  Fleet randomFleet;
  Sea sea;
  List<Position> freeIndexes;

  public RandomFleetGenerator(Sea sea) {
    randomFleet = new Fleet();
    this.sea = sea;
    freeIndexes = IntStream.range(0, 100).boxed().map(i -> new Position(i)).collect(Collectors.toList());

  }

  public Fleet generateRandomFleet() {

    createFleetWithEmptyShips();
    buildShipsInFleet();


    Ship ship = new Ship(1);

//    Random random = new Random();
//    int column = random.nextInt(10);
//    int row = random.nextInt(10);
//    System.out.println(column);
//    System.out.println(row);
//    Field field = new Mast(column, row, FieldSize.BIG);
//    ship.addMast(field);
//
//    randomFleet.addShip(ship);
//
//    System.out.println("generated");

    return randomFleet;
  }

  private void buildShipsInFleet() {
    for(Ship ship :  randomFleet) {
      new ShipBuilder(ship).build();
    }
  }

  private void createFleetWithEmptyShips() {

    List<Integer> shipLengths = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);

    for (Integer length : shipLengths) {
      randomFleet.addShip(new Ship(length));
    }
  }

  private class ShipBuilder {
    private Ship ship;
    private Set<Position> shipMastPosition;
    private Set<Position> posibblePositionsForShip;

    public ShipBuilder(Ship ship) {

      this.ship = ship;
      shipMastPosition = new HashSet<>();
        posibblePositionsForShip =  new HashSet<>();
    }

    public void build() {
      Position firstPosition = getIndexOfFirstMast();
      removeIndexFromFreeIndexes(firstPosition);
      shipMastPosition.add(firstPosition);
      ship.addMast(new Mast(firstPosition, FieldSize.BIG));
      while(!ship.isShipDone()) {
          Position next = findNextPosition(ship.lastMast());
          ship.addMast(new Mast(next, FieldSize.BIG));
          freeIndexes.remove(next);
          shipMastPosition.add(next);
          posibblePositionsForShip.remove(next);
      }
      removeBoundriesFromFreeIndexes();
    }

      private void removeBoundriesFromFreeIndexes() {
          ShipBoundariesPositions shipBoundariesPositions = new ShipBoundariesPositions(sea);
          freeIndexes.removeAll(shipBoundariesPositions.calculateShipBoundariesPositions(ship));
      }

      private Position findNextPosition(Field field) {
        PossiblePositions possiblePositions = new PossiblePositions(sea);
        Set<Position> newPositions = possiblePositions.findPositions(field).stream().map(f -> f.position()).collect(Collectors.toSet());
        newPositions.retainAll(freeIndexes);
        posibblePositionsForShip.addAll(newPositions);
        return randomNextMast();
      }

      private Position randomNextMast() {
        Random random = new Random();
        Integer index = random.nextInt(posibblePositionsForShip.size());
        Position next = new ArrayList<>(posibblePositionsForShip).get(index);
        return next;
      }

      private void removeIndexFromFreeIndexes(Position position) {
          freeIndexes.remove(position);
      }

      private Position getIndexOfFirstMast() {
        Random random = new Random();
        Integer firstIndex = random.nextInt(freeIndexes.size());
        return freeIndexes.get(firstIndex);
      }
  }
}
