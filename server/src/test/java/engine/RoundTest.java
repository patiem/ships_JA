package engine;

import fleet.CustomFleet;
import fleet.Fleet;
import fleet.HardcodedFleetModel;
import model.FleetModel;
import model.ShipModel;
import model.Shot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

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
  public void givenShotOnTargetAndFleetWhenCheckShotTheReturnHitState(Integer shotPosition) {
    Shot shot = new Shot(shotPosition);
    Round round = new Round();

    ShotResult actualShotResult = round.fireShot(fleetUnderFire, shot);
    ShotResult expectedResult = ShotResult.HIT;

    assertEquals(actualShotResult, expectedResult);
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
    ShotResult expectedResult = ShotResult.MISSED;

    assertEquals(actualShotResult, expectedResult);
  }

  @Test
  public void givenShotAgainAndFleetWhenCheckShotThenReturnMissedState() {
    Integer shotPosition = 0;
    Shot shot = new Shot(shotPosition);
    Round round = new Round();

    round.fireShot(fleetUnderFire, shot);
    ShotResult actualShotResult = round.fireShot(fleetUnderFire, shot);
    ShotResult expectedResult = ShotResult.MISSED;

    assertEquals(actualShotResult, expectedResult);
  }


}