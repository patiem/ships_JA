package model;

import gui.fields.ClickableField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SeaTest {

  private Sea sea;

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
    ClickableField returnedSeaField = sea.getSeaFieldByPosition(positionOne);

    //then
    assertThat(returnedSeaField).isEqualTo(fieldOne);
  }

  @Test(expectedExceptions = IndexOutOfBoundsException.class)
  public void whenSeaDoesntContainFieldWithGivenPosition_throws() {

    //given
    ClickableField fieldOne = mock(ClickableField.class);
    //given
    Position positionOne = mock(Position.class);
    when(fieldOne.position()).thenReturn(positionOne);

    Position positionTwo = mock(Position.class);

    //when
    sea.addSeaField(fieldOne);
    sea.getSeaFieldByPosition(positionTwo);
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
  public void givenPosition_markedSeaFieldAsBoundry() {

    //given
    ClickableField fieldOne = mock(ClickableField.class);
    Position positionOne = mock(Position.class);
    when(fieldOne.position()).thenReturn(positionOne);

    //when
    sea.addSeaField(fieldOne);
    sea.makeBoundary(positionOne);

    //then
    verify(fieldOne, times(1)).setIsMarkedAsBound(true);
  }

}