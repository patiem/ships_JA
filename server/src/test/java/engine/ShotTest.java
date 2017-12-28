package engine;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ShotTest {

  @Test
  public void whenAsIntThenReturnShotPosition() {
    Integer shotPosition = 0;
    Shot shot = new Shot(shotPosition);

    Integer actualShotPosition = shot.asInteger();

    assertEquals(actualShotPosition, shotPosition);
  }
}