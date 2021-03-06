package engine;

import fleet.CustomFleet;
import fleet.Fleet;
import fleet.HardcodedFleetModel;
import common.model.FleetModel;
import common.model.ShipModel;
import common.model.Shot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class RoundTest {

  private Fleet fleetUnderFire;

  @BeforeMethod
  public void setUp() {
    ShipModel firstShip = new ShipModel(Arrays.asList(0, 1));
    ShipModel secondShip = new ShipModel(Arrays.asList(10, 11, 12, 13));
    FleetModel fleetModel = new HardcodedFleetModel(Arrays.asList(firstShip, secondShip));
    fleetUnderFire = new CustomFleet(fleetModel);
  }

  @DataProvider(name = "shots on target")
  public Object[] getShotsOnTarget() {
    return new Object[] {0, 1, 10, 11, 12, 13};
  }

  @Test(dataProvider = "shots on target")
  public void givenShotOnTargetAndFleetWhenCheckShotTheReturnShipHit(Integer shotPosition) {
    Shot shot = new Shot(shotPosition);
    Round round = new Round();

    ShotResult actualShotResult = round.fireShot(fleetUnderFire, shot);

    assertThat(actualShotResult).isInstanceOf(ShipHit.class);
  }

  @DataProvider(name = "missed shots")
  public Object[] missedShots() {
    return new Object[] {4, 90, 5, 55, 83, 80};
  }

  @Test(dataProvider = "missed shots")
  public void givenMissedShotAndFleetWhenCheckShotTheReturnMissedState(Integer shotPosition) {
    Shot shot = new Shot(shotPosition);
    Round round = new Round();

    ShotResult actualShotResult = round.fireShot(fleetUnderFire, shot);

    assertThat(actualShotResult).isInstanceOf(MissedShot.class);
  }

  @Test
  public void givenShotAgainAndFleetWhenCheckShotThenReturnHitAgainShot() {
    Integer shotPosition = 0;
    Shot shot = new Shot(shotPosition);
    Round round = new Round();

    round.fireShot(fleetUnderFire, shot);
    ShotResult actualShotResult = round.fireShot(fleetUnderFire, shot);

    assertThat(actualShotResult).isInstanceOf(HitAgainShot.class);
  }

  @Test
  public void givenFleetAndShipPositionsWhenIsHitReturnSunkShip() {
    Shot firstShot = new Shot(0);
    Shot secondShot = new Shot(1);
    Round round = new Round();

    round.fireShot(fleetUnderFire, firstShot);
    ShotResult actualShotResult = round.fireShot(fleetUnderFire, secondShot);

    assertThat(actualShotResult).isInstanceOf(ShipSunk.class);
  }
}
