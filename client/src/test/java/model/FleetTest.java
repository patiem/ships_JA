package model;

import gui.fields.Field;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FleetTest {

  private Fleet fleet;

  @BeforeMethod
  public void setup() {
    fleet = new Fleet();
  }

  @Test
  public void returnsPositionsOfAllMast() {

    //given
    Position one = new Position(1);
    Position two = new Position(2);
    Position three = new Position(3);

    Ship shipOne = mock(Ship.class);
    List<Position> positionOfShipOne = new ArrayList<>(Arrays.asList(one));
    when(shipOne.positionsOfAllMastInShip()).thenReturn(positionOfShipOne);

    Ship shipTwo = mock(Ship.class);
    List<Position> positionOfShipTwo = new ArrayList<>(Arrays.asList(two, three));
    when(shipTwo.positionsOfAllMastInShip()).thenReturn(positionOfShipTwo);

    List<Position> expectedPositions = new ArrayList<>(Arrays.asList(one, two, three));

    //when
    fleet.addShip(shipOne);
    fleet.addShip(shipTwo);
    List<Position> returnedPositions = fleet.mastsPositions();

    //then
    assertThat(returnedPositions).isEqualTo(expectedPositions);
  }

  @Test
  public void descriptiveNameGoesHere() {

      //given
    Position one = new Position(1);
    Position two = new Position(2);
    Position three = new Position(3);

    Field fieldOne = mock(Field.class);
    when(fieldOne.position()).thenReturn(one);
    Ship shipOne = new Ship(1);
    shipOne.addMast(fieldOne);

    Field fieldTwo = mock(Field.class);
    when(fieldTwo.position()).thenReturn(two);
    Field fieldThree= mock(Field.class);
    when(fieldThree.position()).thenReturn(three);
    Ship shipTwo = new Ship(2);
    shipTwo.addMast(fieldTwo);
    shipTwo.addMast(fieldThree);

    List<Position> expectedPositions = new ArrayList<>(Arrays.asList(one, two, three));

    //when
    fleet.addShip(shipOne);
    fleet.addShip(shipTwo);
    List<Position> returnedPositions = fleet.mastsPositions();

    //then
    assertThat(returnedPositions).isEqualTo(expectedPositions);
  }
}
