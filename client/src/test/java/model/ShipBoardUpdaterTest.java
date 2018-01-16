package model;

import gui.fields.Field;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class ShipBoardUpdaterTest {

  private Ship mockedShip;
  private PossiblePositions possiblePositions;
  private ShipBoundariesPositions shipBoundariesPositions;
  private SeaCleaner seaCleaner;
  private ShipBoardUpdater boardUpdater;

  @BeforeMethod
  public void setup() {
    mockedShip = mock(Ship.class);
    possiblePositions = mock(PossiblePositions.class);
    shipBoundariesPositions = mock(ShipBoundariesPositions.class);
    seaCleaner = mock(SeaCleaner.class);
    boardUpdater = new ShipBoardUpdater(possiblePositions, shipBoundariesPositions, seaCleaner);
  }

  @Test
  public void ifShipIsNotBuiltPossiblePositionsArePerform() {

    //given
    Field mast = mock(Field.class);
    when(mockedShip.isShipDone()).thenReturn(false);
    when(mockedShip.lastMast()).thenReturn(mast);

    //when
    boardUpdater.update(mockedShip);

    //then
    verify(possiblePositions).findPositions(mast);
  }

  @Test
  public void ifShipIsBuiltBoundariesPositionsArePerformAndSeaIsCleaned() {

    //given
    when(mockedShip.isShipDone()).thenReturn(true);

    //when
    boardUpdater.update(mockedShip);
    
    //then
    verify(shipBoundariesPositions).markSeaAsBoundary();
    verify(seaCleaner).clean();
  }

}