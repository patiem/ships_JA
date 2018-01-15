package model;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FleetMapperTest {

  @Test
  public void whenMapToModelThenReturnCorrectlyMappedFleet() {
    Integer[][] shipsPositions = {{0, 1, 2}, {55, 56}};

    Fleet fleetToMap = createFleetFromPositions(shipsPositions);

    FleetModel actualModel = FleetMapper.mapToFleetModel(fleetToMap);
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

  private Fleet createFleetFromPositions(Integer[][] fleetPositions) {
    Fleet fleet = new Fleet();

    Arrays.stream(fleetPositions)
        .forEach(shipPositions -> fleet.addShip(createShipFromPositions(shipPositions)));

    return fleet;
  }

  private Ship createShipFromPositions(Integer[] positions) {
    Ship ship = new Ship(positions.length);

    Arrays.stream(positions)
        .forEach(position -> ship.addMast(new DummyMast(position)));

    return ship;
  }
}
