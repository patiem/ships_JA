package communication;

import engine.HitChecker;
import engine.ShotResult;
import fleet.CustomFleet;
import fleet.Fleet;
import fleet.HardcodedFleet;
import fleet.HardcodedFleetModel;
import model.FleetModel;
import model.ShipModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

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
  public void givenHardcodedFleetAndMastPositionWhenIsHitReturnHit() {
    int position = 10;

    ShotResult actualState = hitChecker.checkShot(position);
    assertEquals(actualState, ShotResult.HIT);
  }

  @Test
  public void givenHardcodedFleetIncorrectMastPositionWhenIsHitReturnMissed() {
    int position = 0;

    ShotResult actualState = hitChecker.checkShot(position);
    assertEquals(actualState, ShotResult.MISSED);
  }

  @Test
  public void givenHardcodedFleetAndMastPositionAlreadyHitWhenIsHitReturnMissed() {
    HitChecker hitChecker = new HitChecker(fleet);
    int position = 2;

    fleet.hit(position);

    ShotResult actualState = hitChecker.checkShot(position);
    assertEquals(actualState, ShotResult.MISSED);
  }
}