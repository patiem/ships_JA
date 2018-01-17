package model;

import gui.fields.Mast;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class MastBoundariesPositionsTest extends DataProvider {

  private Mast mast;

  @BeforeMethod
  public void setup() {
    mast = mock(Mast.class);
  }

  @Test(dataProvider = "fieldsInside")
  public void whenMastInsideBoard_ListWithBoundariesIsFull(int column, int row) {

    //given
    when(mast.getColumn()).thenReturn(column);
    when(mast.getRow()).thenReturn(row);
    MastBoundariesPositions boundariesPosition = new MastBoundariesPositions(mast);
    int expectedListSize = 9;
    //when
    List<Position> positions = boundariesPosition.countBoundariesForMast();
    //then
    assertEquals(positions.size(), expectedListSize);
  }

  @Test(dataProvider = "fieldsInCorners")
  public void whenMastIsInCorner_ListWithBoundariesHasOnlyFourPositions(int column, int row) {

    //given
    Mast mast = mock(Mast.class);
    when(mast.getColumn()).thenReturn(column);
    when(mast.getRow()).thenReturn(row);
    MastBoundariesPositions boundariesPosition = new MastBoundariesPositions(mast);
    int expectedListSize = 4;
    //when
    List<Position> positions = boundariesPosition.countBoundariesForMast();
    //then
    assertEquals(positions.size(), expectedListSize);
  }

  @Test(dataProvider = "fieldsOnBoundaries")
  public void whenMastIsOnBoundary_ListWithBoundariesHasSixPositions(int column, int row) {

    //given
    Mast mast = mock(Mast.class);
    when(mast.getColumn()).thenReturn(column);
    when(mast.getRow()).thenReturn(row);
    MastBoundariesPositions boundariesPosition = new MastBoundariesPositions(mast);
    int expectedListSize = 6;
    //when
    List<Position> positions = boundariesPosition.countBoundariesForMast();
    //then
    assertEquals(positions.size(), expectedListSize);
  }
}