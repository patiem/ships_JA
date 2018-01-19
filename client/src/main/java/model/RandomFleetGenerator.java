package model;

import gui.fields.Field;
import gui.fields.FieldSize;
import gui.fields.Mast;

import java.util.Random;

public class RandomFleetGenerator {
  public RandomFleetGenerator() {
  }

  public Fleet generateRandomFleet() {
    Fleet randomFleet = new Fleet();

    Ship ship = new Ship(1);

    Random random = new Random();
    int column = random.nextInt(10);
    int row = random.nextInt(10);
    System.out.println(column);
    System.out.println(row);
    Field field = new Mast(column, row, FieldSize.BIG);
    ship.addMast(field);

    randomFleet.addShip(ship);

    System.out.println("generated");

    return randomFleet;
  }
}
