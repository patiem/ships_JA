package model;

import java.util.Random;

public class DataProvider {

  @org.testng.annotations.DataProvider(name = "fieldsInside")
  public static Object[][] fieldsInside() {

    int startColumn = 2;
    int endColumn = 7;
    int startRow = 2;
    int endRow = 7;
    int counter = 0;

    Object[][] fields = new Object[endRow * endColumn][];

    for (int column = startColumn; column <= endColumn; column++) {
      for (int row = startRow; row <= endRow; row++) {
        fields[counter++] = new Object[] {column, row};
      }
    }
    return fields;
  }

  @org.testng.annotations.DataProvider(name = "fieldsOnBoundaries")
  public static Object[] fieldsOnBoundaries() {

    return new Object[][] {{0, 2}, {0, 5}, {0, 7},
        {9, 1}, {9, 3}, {9, 6},
        {2, 0}, {4, 0}, {6, 0},
        {1, 9}, {3, 9}, {8, 9}};
  }

  @org.testng.annotations.DataProvider(name = "fieldsInCorners")
  public static Object[][] fieldsInCorners() {

    Object[][] fields = new Object[4][];

    int cornerRowMin = 0;
    int cornerRowMax = 9;
    int cornerColMin = 0;
    int cornerColMax = 9;

    fields[0] = new Object[] {cornerRowMin, cornerColMin};
    fields[1] = new Object[] {cornerRowMin, cornerColMax};
    fields[2] = new Object[] {cornerRowMax, cornerColMin};
    fields[3] = new Object[] {cornerRowMax, cornerColMax};

    return fields;
  }

  @org.testng.annotations.DataProvider(name = "positionCoordinates")
  public static Object[] coordinates() {

    Object[] coordinates = {71, 96, 78, 89, 26, 25, 23, 41, 44, 18};
    return coordinates;
  }

  @org.testng.annotations.DataProvider(name = "positions")
  public static Object[][] positions() {

    int testCount = 10;
    int boundForRandom = 99;
    Object[][] positionsIndexes = new Object[testCount][];

    for (int i = 0; i < testCount; i++) {
      Random random = new Random();
      int index = random.nextInt(boundForRandom);
      positionsIndexes[i] = new Object[] {index};
    }
    return positionsIndexes;
  }
}
