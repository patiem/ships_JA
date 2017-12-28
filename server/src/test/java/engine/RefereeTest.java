package engine;

import fleet.Fleet;
import fleet.HardcodedFleet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class RefereeTest {
  private Referee referee;

  @BeforeMethod
  public void setUp() {
    referee = new Referee();
  }

  @Test
  public void givenAliveFleetWhenIsWonThenReturnGameStateActive() {
    Fleet aliveFleet = new HardcodedFleet();
    GameState expectedState = GameState.ACTIVE;

    GameState actualState = referee.isVictory(aliveFleet);

    assertEquals(actualState, expectedState);
  }

  @Test
  public void givenSunkFleetWhenIsVictoryThenReturnGameStateActive() {
    Fleet sunkFleet = mock(Fleet.class);
    when(sunkFleet.isSunk()).thenReturn(true);

    GameState expectedState = GameState.WIN;
    GameState actualState = referee.isVictory(sunkFleet);

    assertEquals(actualState, expectedState);
  }
}