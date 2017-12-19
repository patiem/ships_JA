package engine;

import fleet.Fleet;
import fleet.HardcodedFleet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class RefereeTest {
    private Referee referee;

    @BeforeMethod
    public void setUp(){
        referee = new Referee();
    }

    @Test
    public void givenAliveFleetWhenIsWonThenReturnFalse(){
        Fleet aliveFleet = new HardcodedFleet();

        boolean actualResult = referee.isVictory(aliveFleet);

        assertFalse(actualResult);
    }

    @Test
    public void givenSunkFleetWhenIsVictoryThenReturnTrue(){
        Fleet sunkFleet = mock(Fleet.class);
        when(sunkFleet.isSunk()).thenReturn(true);

        boolean actualResult = referee.isVictory(sunkFleet);

        assertTrue(actualResult);
    }


}