package model;

import gui.fields.ClickableField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.mockito.Mockito.*;

public class SeaTest extends DataProvider {

  private Sea sea;
  private SoftAssert softAssert = new SoftAssert();

  @BeforeMethod
  public void setup() {
    sea = new Sea();
  }

  @Test
  public void whenSeaHasFieldWithGivenPosition_returnsIt() {

    //given
    ClickableField fieldOne = mock(ClickableField.class);
    Position positionOne = mock(Position.class);
    when(fieldOne.position()).thenReturn(positionOne);

    ClickableField fieldTwo = mock(ClickableField.class);
    Position positionTwo = mock(Position.class);
    when(fieldTwo.position()).thenReturn(positionTwo);

    //when
    sea.addSeaField(fieldOne);
    sea.addSeaField(fieldTwo);
    ClickableField returnedSeaFieldOne = sea.getSeaFieldByPosition(positionOne);
    ClickableField returnedSeaFieldTwo = sea.getSeaFieldByPosition(positionTwo);

    //then
    softAssert.assertEquals(returnedSeaFieldOne, fieldOne);
    softAssert.assertEquals(returnedSeaFieldTwo, fieldTwo);
    softAssert.assertAll();
  }

  @Test(expectedExceptions = IndexOutOfBoundsException.class, dataProvider = "positionCoordinates")
  public void whenSeaDoesntContainFieldWithGivenPosition_throws(int index) {

    //given
    Integer row = 0;
    Integer col = 0;
    ClickableField fieldOne = new DummyClickableField(row, col);
    Position testPosition = new Position(index);

    //when
    sea.addSeaField(fieldOne);
    sea.getSeaFieldByPosition(testPosition);
  }

  @Test
  public void whenSeaIsCleared_fieldsAreMadeUnclickable() {

    //given
    ClickableField fieldOne = mock(ClickableField.class);
    ClickableField fieldTwo = mock(ClickableField.class);

    //when
    sea.addSeaField(fieldOne);
    sea.addSeaField(fieldTwo);
    sea.clearSea();
    //then
    verify(fieldOne, times(1)).makeUnclickable();
    verify(fieldTwo, times(1)).makeUnclickable();
  }

  @Test
  public void givenPosition_markedSeaFieldAsBoundary() {

    //given
    ClickableField fieldOne = mock(ClickableField.class);
    Position positionOne = mock(Position.class);
    when(fieldOne.position()).thenReturn(positionOne);

    //when
    sea.addSeaField(fieldOne);
    sea.makeBoundary(positionOne);

    //then
    verify(fieldOne, times(1)).markAsBound(true);
  }

}