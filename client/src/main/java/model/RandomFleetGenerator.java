package model;

import gui.fields.Field;
import gui.fields.FieldSize;
import gui.fields.Mast;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomFleetGenerator {

  private Fleet randomFleet;
  private Sea sea;
  private List<Position> freeIndexes;

  public RandomFleetGenerator(Sea sea) {
    randomFleet = new Fleet();
    this.sea = sea;
    freeIndexes = IntStream.range(0, 100).boxed().map(Position::new).collect(Collectors.toList());
  }

  public Fleet generateRandomFleet() {
    createFleetWithEmptyShips();
    buildShipsInFleet();
    return randomFleet;
  }

  private void createFleetWithEmptyShips() {
    List<Integer> shipLengths = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);
    for (Integer length : shipLengths) {
      randomFleet.addShip(new Ship(length));
    }
  }

  private void buildShipsInFleet() {
    for (Ship ship : randomFleet) {
      new RandomShipCreator(ship).randomize();
    }
  }

  private class RandomShipCreator {
    private Ship ship;
    private Set<Position> possiblePositionsForShip;

    RandomShipCreator(Ship ship) {
      this.ship = ship;
      possiblePositionsForShip = new HashSet<>();
    }

    void randomize() {
      findFirstMast();

      while (!ship.isShipDone()) {
        findNextMasts();
      }
      removeBoundariesFromFreeIndexes();
    }

    private void findFirstMast() {
      Position firstPosition = randomMast(freeIndexes);
      removeIndexFromFreeIndexes(firstPosition);
      addNewMastToShip(firstPosition);
    }

    private void findNextMasts() {
      Position next = findNextPosition(ship.lastMast());
      addNewMastToShip(next);
      removeIndexFromFreeIndexes(next);
      possiblePositionsForShip.remove(next);
    }

    private void addNewMastToShip(Position firstPosition) {
      ship.addMast(new Mast(firstPosition, FieldSize.BIG));
    }


    private Position findNextPosition(Field field) {
      PossiblePositions possiblePositions = new PossiblePositions(sea);
      Set<Position> newPositions =
          possiblePositions
              .findPositions(field)
              .stream()
              .map(f -> f.position())
              .collect(Collectors.toSet());
      newPositions.retainAll(freeIndexes);
      possiblePositionsForShip.addAll(newPositions);
      return randomMast(new ArrayList<>(possiblePositionsForShip));
    }

    private void removeBoundariesFromFreeIndexes() {
      ShipBoundariesPositions shipBoundariesPositions = new ShipBoundariesPositions(sea);
      freeIndexes.removeAll(shipBoundariesPositions.calculateShipBoundariesPositions(ship));
    }

    private void removeIndexFromFreeIndexes(Position position) {
      freeIndexes.remove(position);
    }


    private Position randomMast(List<Position> positions) {
      Random random = new Random();
      Integer firstIndex = random.nextInt(positions.size());
      return positions.get(firstIndex);
    }

  }

}
