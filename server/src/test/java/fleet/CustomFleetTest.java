package fleet;

import model.FleetModel;
import org.testng.annotations.Test;

import java.util.List;

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
}