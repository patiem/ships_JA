package model;

import gui.fields.Field;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ShipMapperTest {

  @Test
  public void whenMapToModelThenReturnCorrectlyMappedShip() {
    int expectedNumberOfMasts = 2;
    int firstPosition = 0;
    int secondPosition = 1;
    List<Integer> expectedFields = Arrays.asList(firstPosition, secondPosition);

    Field firstMast = new DummyMast(firstPosition);
    Field secondMast = new DummyMast(secondPosition);

    Ship shipToMap = new Ship(expectedNumberOfMasts);
    shipToMap.addMast(firstMast);
    shipToMap.addMast(secondMast);

    ShipModel actualResult = ShipMapper.mapToModel(shipToMap);

    SoftAssertions soft = new SoftAssertions();
    soft.assertThat(actualResult.getFields().containsAll(expectedFields));
    soft.assertThat(actualResult.getNumberOfMasts()).isEqualTo(expectedNumberOfMasts);
    soft.assertAll();
  }
}
