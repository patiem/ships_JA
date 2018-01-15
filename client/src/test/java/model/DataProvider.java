package model;


import java.util.Random;

public class DataProvider {

  @org.testng.annotations.DataProvider(name = "fieldsInside")
  public static Object[][] scenarios() {

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

  @org.testng.annotations.DataProvider(name = "positionCoordinates")
  public static Object[][] coordinates() {

    int testCount = 5;
    int boundForRandom = 99;
    Object[][] coordinates = new Object[testCount][];

    for(int i = 0; i < testCount; i++) {
      Random random = new Random();
      int coordinateGTZero = random.nextInt(boundForRandom) + 1;
      coordinates[i] = new Object[] {coordinateGTZero};
    }
    return coordinates;
  }

}
