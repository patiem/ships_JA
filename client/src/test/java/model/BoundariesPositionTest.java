package model;

import gui.fields.Mast;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class BoundariesPositionTest extends DataProvider {

  @Test(dataProvider = "fieldsInside")
  public void whenMastInsideBoardListWithBoundariesIsFull(int column, int row) {

    //given
    Mast mast = mock(Mast.class);
    when(mast.getColumn()).thenReturn(column);
    when(mast.getRow()).thenReturn(row);
    MastBoundariesPositions boundariesPosition = new MastBoundariesPositions(mast);
    int expectedListSize = 9;
    //when
    List<Position> positions = boundariesPosition.countBoundariesForMast();
    //then
    assertEquals(positions.size(), expectedListSize);
  }


}