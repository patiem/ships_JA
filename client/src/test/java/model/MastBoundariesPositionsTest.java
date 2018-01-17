package model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class MastBoundariesPositionsTest extends DataProvider {

  private Position position;

  @BeforeMethod
  public void setup() {
    position = mock(Position.class);
  }

  @Test(dataProvider = "fieldsInside")
  public void whenMastInsideBoard_ListWithBoundariesIsFull(int column, int row) {

    //given
    when(position.getColumn()).thenReturn(column);
    when(position.getRow()).thenReturn(row);
    MastBoundariesPositions boundariesPosition = new MastBoundariesPositions(position);
    int expectedListSize = 9;
    //when
    List<Position> positions = boundariesPosition.countBoundariesForMast();
    //then
    assertEquals(positions.size(), expectedListSize);
  }

  @Test(dataProvider = "fieldsInCorners")
  public void whenMastIsInCorner_ListWithBoundariesHasOnlyFourPositions(int column, int row) {

    //given
    when(position.getColumn()).thenReturn(column);
    when(position.getRow()).thenReturn(row);
    MastBoundariesPositions boundariesPosition = new MastBoundariesPositions(position);
    int expectedListSize = 4;
    //when
    List<Position> positions = boundariesPosition.countBoundariesForMast();
    //then
    assertEquals(positions.size(), expectedListSize);
  }

  @Test(dataProvider = "fieldsOnBoundaries")
  public void whenMastIsOnBoundary_ListWithBoundariesHasSixPositions(int column, int row) {

    //given
    when(position.getColumn()).thenReturn(column);
    when(position.getRow()).thenReturn(row);
    MastBoundariesPositions boundariesPosition = new MastBoundariesPositions(position);
    int expectedListSize = 6;
    //when
    List<Position> positions = boundariesPosition.countBoundariesForMast();
    //then
    assertEquals(positions.size(), expectedListSize);
  }
}