package engine;

import fleet.Fleet;
import fleet.HardcodedFleet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RoundTest {

  @DataProvider(name = "shots on target")
  public Object[] getShotsOnTarget() {
    return new Object[] {0, 3, 17, 44, 34, 99}; //HardcodedFleet positions
  }

  @Test(dataProvider = "shots on target")
  public void givenShotOnTargetAndFleetWhenCheckShotTheReturnHitState(Integer shotPosition) {
    Fleet fleetUnderFire = new HardcodedFleet();
    Shot shot = new Shot(shotPosition);
    Round round = new Round();

    ShotResult actualShotResult = round.makeShot(fleetUnderFire, shot);
    ShotResult expectedResult = ShotResult.HIT;

    assertEquals(actualShotResult, expectedResult);
  }

  @DataProvider(name = "missed shots")
  public Object[] missedShots() {
    return new Object[] {4, 90, 5, 55, 83, 80};
  }

  @Test(dataProvider = "missed shots")
  public void givenMissedShotAndFleetWhenCheckShotTheReturnMissedState(Integer shotPosition) {
    Fleet fleetUnderFire = new HardcodedFleet();
    Shot shot = new Shot(shotPosition);
    Round round = new Round();

    ShotResult actualShotResult = round.makeShot(fleetUnderFire, shot);
    ShotResult expectedResult = ShotResult.MISSED;

    assertEquals(actualShotResult, expectedResult);
  }

  @Test
  public void givenShotAgainAndFleetWhenCheckShotThenReturnHitAgainState() {
    Integer shotPosition = 0;
    Fleet fleetUnderFire = new HardcodedFleet();
    Shot shot = new Shot(shotPosition);
    Round round = new Round();

    round.makeShot(fleetUnderFire, shot);
    ShotResult actualShotResult = round.makeShot(fleetUnderFire, shot);
    ShotResult expectedResult = ShotResult.HIT_AGAIN;

    assertEquals(actualShotResult, expectedResult);
  }


}