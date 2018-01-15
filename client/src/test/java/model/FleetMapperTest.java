package model;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FleetMapperTest {

  @Test
  public void shouldReturnFleetModelIncludingProvidedFleet() {
    Integer[][] shipsPositions = {{0, 1, 2}, {55, 56}};

    Fleet mockedFleetToMap = mock(Fleet.class);
    List<Ship> ships = new ArrayList<>();
    Ship firstShip = mock(Ship.class);
    when(firstShip.positionsOfAllMastInShipAsIntegers()).thenReturn(Arrays.asList(shipsPositions[0]));
    Ship secondShip = mock(Ship.class);
    when(secondShip.positionsOfAllMastInShipAsIntegers()).thenReturn(Arrays.asList(shipsPositions[1]));
    ships.add(firstShip);
    ships.add(secondShip);
    when(mockedFleetToMap.getShips()).thenReturn(ships);

    FleetModel actualModel = FleetMapper.mapToFleetModel(mockedFleetToMap);
    List<Integer> actualPositions = actualModel.getShips()
        .stream()
        .map(ShipModel::getFields)
        .flatMap(List::stream)
        .collect(Collectors.toList());

    SoftAssertions soft = new SoftAssertions();
    soft.assertThat(actualPositions.containsAll(Arrays.asList(shipsPositions[0])));
    soft.assertThat(actualPositions.containsAll(Arrays.asList(shipsPositions[1])));
    soft.assertAll();
  }
}
