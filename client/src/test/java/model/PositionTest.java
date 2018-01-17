package model;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PositionTest extends DataProvider{

  @Test(dataProvider = "positions")
  public void upReturnsPositionThatHasRowValueOriginalMinusOneAndColIsTheSame(int index) {

      //given
    Position originalPosition = new Position(index);
      //when
    Position upPosition = Position.up(originalPosition);
      //then
    assertThat(upPosition.getRow()).isEqualTo(originalPosition.getRow() - 1);
    assertThat(upPosition.getColumn()).isEqualTo(originalPosition.getColumn());
  }

  @Test(dataProvider = "positions")
  public void downReturnsPositionThatHasRowValueOriginalPlusOneAndColIsTheSame(int index) {

      //given
    Position originalPosition = new Position(index);
      //when
    Position downPosition = Position.down(originalPosition);
      //then
    assertThat(downPosition.getRow()).isEqualTo(originalPosition.getRow() + 1);
    assertThat(downPosition.getColumn()).isEqualTo(originalPosition.getColumn());
  }

  @Test(dataProvider = "positions")
  public void leftReturnsPositionThatHasColValueOriginalMinusOneAndRowIsTheSame(int index) {

      //given
    Position originalPosition = new Position(index);
      //when
    Position leftPosition = Position.left(originalPosition);
      //then
    assertThat(leftPosition.getColumn()).isEqualTo(originalPosition.getColumn() - 1);
    assertThat(leftPosition.getRow()).isEqualTo(originalPosition.getRow());

  }

  @Test(dataProvider = "positions")
  public void rightReturnsPositionThatHasColValueOriginalPlusOneAndRowIsTheSame(int index) {

      //given
    Position originalPosition = new Position(index);
      //when
    Position rightPosition = Position.right(originalPosition);
      //then
    assertThat(rightPosition.getColumn()).isEqualTo(originalPosition.getColumn() + 1);
    assertThat(rightPosition.getRow()).isEqualTo(originalPosition.getRow());
  }

  @Test(dataProvider = "positions")
  public void positionsThatHasSameColAndRowAreEquals(int index) {

      //given
    Position positionOne = new Position(index);
    Position positionTwo = new Position(index);

      //when
      //then
    assertTrue(positionOne.equals(positionTwo));
  }

  @Test(dataProvider = "positions")
  public void positionsThatHasDifferentIndexAreEquals(int index) {

      //given
    Position positionOne = new Position(index);
    Position positionTwo = new Position(index + 2);

      //when
      //then
    assertFalse(positionOne.equals(positionTwo));
  }


}