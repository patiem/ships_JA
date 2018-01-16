package model;

import gui.fields.Field;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ShipCreatorTest {

  @Test
  public void whenShipIsNotFullyBuildMastIsAddedToShip() {

    //given
    int shipLength = 2;
    Ship ship = new Ship(shipLength);
    Field mast = mock(Field.class);

    ShipCreator shipCreator = new ShipCreator(ship);

    //when
    shipCreator.addMastToShip(mast);

    //then
    assertThat(ship.lastMast()).isEqualTo(mast);
  }

  @Test(expectedExceptions = IllegalStateException.class,
      expectedExceptionsMessageRegExp = "Ship is already build")
  public void whenShipIsFullyBuildAddingNextMastThrowsEx() {

    //given
    int shipLength = 1;
    Ship ship = new Ship(shipLength);
    Field mast = mock(Field.class);
    Field mastTwo = mock(Field.class);

    ShipCreator shipCreator = new ShipCreator(ship);

    //when
    shipCreator.addMastToShip(mast);
    shipCreator.addMastToShip(mastTwo);

  }
}