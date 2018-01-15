package fleet;

import model.FleetModel;
import model.ShipModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CustomFleetTest {

  @Test
  public void givenPositionWhenHitThenPositionAddedToHitFields() {
    FleetModel fleetModel = mock(FleetModel.class);
    CustomFleet fleet = new CustomFleet(fleetModel);
    int hitPosition = 1;

    fleet.hit(hitPosition);
    List<Integer> actualHits = fleet.getHitFields();

    assertThat(actualHits).contains(hitPosition);
  }

  @Test
  public void givenPositionWhenGetShipByPositionThenReturnShipModel() {
    //Given
    Integer[][] fleetPositions = {
        {0, 1},
        {3, 4, 5},
        {99, 98, 97, 96}
    };

    Integer[] expectedShipPositions = fleetPositions[1];
    Integer positionToSearch = fleetPositions[1][1];

    FleetModel fleetModel = createFleetFromPositions(fleetPositions);
    CustomFleet fleet = new CustomFleet(fleetModel);

    //When
    ShipModel actualShip = fleet.getShipByPosition(positionToSearch);

    //Then
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(actualShip.getNumberOfMasts()).isEqualTo(expectedShipPositions.length);
    softAssertions.assertThat(actualShip.getFields()).containsExactlyInAnyOrder(expectedShipPositions);
    softAssertions.assertAll();
  }

  private FleetModel createFleetFromPositions(Integer[][] fleetPositions) {
    List<ShipModel> ships = Arrays.stream(fleetPositions)
        .map(this::createShipFromPositions)
        .collect(Collectors.toList());

    return new FleetModel(ships);
  }

  private ShipModel createShipFromPositions(Integer[] positions) {
    return new ShipModel(Arrays.asList(positions));
  }
}