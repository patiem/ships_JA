package engine;

import fleet.CustomFleet;
import fleet.Fleet;
import fleet.HardcodedFleetModel;
import model.FleetModel;
import model.ShipModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class HitCheckerTest {

  private Fleet fleet;
  private HitChecker hitChecker;

  @BeforeMethod
  public void setUp(){
    ShipModel firstShip = new ShipModel(Arrays.asList(2, 1));
    ShipModel secondShip = new ShipModel(Arrays.asList(10, 11, 12, 13));
    FleetModel fleetModel = new HardcodedFleetModel(Arrays.asList(firstShip, secondShip));
    fleet = new CustomFleet(fleetModel);
    hitChecker = new HitChecker(fleet);
  }

  @Test
  public void givenFleetAndMastPositionWhenIsHitReturnHit() {
    int position = 10;

    ShotResult actualShotResult = hitChecker.checkShotFixed(position);
    assertThat(actualShotResult).isInstanceOf(ShipHit.class);
  }

  @Test
  public void givenFleetIncorrectMastPositionWhenIsHitReturnMissedShot() {
    int position = 0;

    ShotResult actualShotResult = hitChecker.checkShotFixed(position);
    assertThat(actualShotResult).isInstanceOf(MissedShot.class);
  }

  @Test
  public void givenFleetAndMastPositionAlreadyHitWhenIsHitReturnMissed() {
    HitChecker hitChecker = new HitChecker(fleet);
    int position = 2;

    fleet.hit(position);

    ShotResult actualShotResult = hitChecker.checkShotFixed(position);
    assertThat(actualShotResult).isInstanceOf(MissedShot.class); //TODO: add MissedAgain!
  }

  @Test
  public void givenFleetAndShipPositionsWhenIsHitReturnSunkShip() {
    HitChecker hitChecker = new HitChecker(fleet);
    int firstShotPosition = 2;
    int secondShotPosition = 1;

    fleet.hit(firstShotPosition);

    ShotResult actualShotResult = hitChecker.checkShotFixed(secondShotPosition);
    assertThat(actualShotResult).isInstanceOf(ShipSunk.class);
  }
}