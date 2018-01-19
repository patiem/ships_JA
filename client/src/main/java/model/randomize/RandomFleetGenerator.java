package model.randomize;

import model.Fleet;
import model.Sea;
import model.Ship;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomFleetGenerator {

  private Fleet randomFleet;
  private Sea sea;
  private List<Integer> freeIndexes;

  public RandomFleetGenerator(Sea sea) {
    this.sea = sea;
    freeIndexes = IntStream.range(0, 100).boxed().collect(Collectors.toList());
  }

  public Fleet generateRandomFleet() {

    randomFleet = populateFleetWithEmptyShips();

    for(Ship ship : randomFleet) {
      findShipPositions(ship);
    }
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

  private void findShipPositions(Ship ship) {

  }

  private Fleet populateFleetWithEmptyShips() {
    Fleet fleet = new Fleet();

    List<Integer> shipLengths = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);

    for(Integer length : shipLengths) {
      fleet.addShip(new Ship(length));
    }
    return fleet;
  }
}
