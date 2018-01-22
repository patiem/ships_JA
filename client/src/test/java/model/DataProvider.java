package model;

import java.util.Arrays;
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

  @org.testng.annotations.DataProvider(name = "shipInCenter")
  public static Object[][] shipInCenter() {

    Object[][] boundaries = {
        //Ships in center
        {Arrays.asList(25, 26, 36, 46), Arrays.asList(14, 15, 16, 17, 24, 27, 34, 35, 37, 45, 47, 55, 56, 57)},
        {Arrays.asList(33, 42, 43, 44), Arrays.asList(22, 23, 24, 31, 32, 34, 35, 41, 45, 51, 52, 53, 54, 55)},
        //Ships in corners
        {Arrays.asList(8, 9, 19, 29), Arrays.asList(7, 17, 18, 28, 38, 39)},
        {Arrays.asList(71, 80, 81, 91), Arrays.asList(60, 61, 62, 70, 72, 82, 90, 92)}};

    return boundaries;
  }
}
