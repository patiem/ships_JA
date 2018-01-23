package model;

import gui.fields.ClickableField;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomFleetGeneratorTest {

  private SoftAssert softAssert = new SoftAssert();

  @Test
  public void returnsFleetWithTenShipsThatAllAreFullyBuilt() {

    //given
    Sea sea = generateSea();
    RandomFleetGenerator randomFleetGenerator = new RandomFleetGenerator(sea);

    int expectedAmountOfShips = 10;

    //when
    Fleet returnedFleet = randomFleetGenerator.generateRandomFleet();

    //then
    softAssert.assertEquals(returnedFleet.getShips().size(), expectedAmountOfShips);
    for (Ship ship : returnedFleet) {
      softAssert.assertTrue(ship.isShipDone());
    }
    softAssert.assertAll();
  }

  private Sea generateSea() {
    Sea sea = new Sea();
    for (int i = 0; i < 100; i++) {
      ClickableField field = mock(ClickableField.class);
      when(field.position()).thenReturn(new Position(i));
      sea.addSeaField(field);
    }
    return sea;
  }
}