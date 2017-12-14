package communication;

import fleet.Fleet;
import fleet.HardcodedFleet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HitCheckerTest {

    @Test
    public void givenHardcodedFleetAndMastPositionWhenIsHitReturnHit() {
        HitChecker hitChecker = new HitChecker(new HardcodedFleet());
        int position = 0;

        ShotState actualState = hitChecker.checkShot(position);
        assertEquals(actualState, ShotState.HIT);
    }

    @Test
    public void givenHardcodedFleetIncorrectMastPositionWhenIsHitReturnMissed(){
        HitChecker hitChecker = new HitChecker(new HardcodedFleet());
        int position = 10;

        ShotState actualState = hitChecker.checkShot(position);
        assertEquals(actualState, ShotState.MISSED);
    }

    @Test
    public void givenHardcodedFleetAndMastPositionAlreadyHitWhenIsHitReturnHitAgain(){
        Fleet fleet = new HardcodedFleet();

        HitChecker hitChecker = new HitChecker(fleet);
        int position = 0;

        fleet.hit(position);

        ShotState actualState = hitChecker.checkShot(position);
        assertEquals(actualState, ShotState.HIT_AGAIN);
    }
}