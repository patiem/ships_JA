package model;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipBoundariesPositionsTest extends DataProvider{

  @Test(dataProvider = "shipInCenter")
  public void forShipFourPlacedInCenter_ReturnsExpectedSetOfPositionsAroundShip(List<Integer> masts, List<Integer> bounds) {
    Sea sea = mock(Sea.class);
    Ship ship = mock(Ship.class);
    List<Integer> listOfPositionIndexesForShip = masts;
    when(ship.positionsOfAllMastInShipAsIntegers()).thenReturn(listOfPositionIndexesForShip);
    ShipBoundariesPositions shipBoundariesPositions = new ShipBoundariesPositions(sea);
    Set<Position> expectedPositions = bounds.stream().map(b -> new Position(b)).collect(Collectors.toSet());
    //when
    Set<Position> returnedPositions = shipBoundariesPositions.calculateShipBoundariesPositions(ship);
    //then
    assertThat(returnedPositions).hasSameElementsAs(expectedPositions);
  }


}