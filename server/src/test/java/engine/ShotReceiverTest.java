package engine;

import model.Shot;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShotReceiverTest {

  @DataProvider(name = "shots input")
  public Object[] shotsInput() {
    return new Object[] {
        0,
        52,
        90
    };
  }

  @Test(dataProvider = "shots input")
  public void givenBufferedReaderWhenReadShotThenReturnCorrectShot(Integer shot) throws IOException {
    ShotReceiver shotReceiver = new SocketShotReceiver();
    BufferedReader mockedReader = mock(BufferedReader.class);
    String mockedMessage = String.format("{\"shot\":{\"position\":%s}}", shot);
    when(mockedReader.readLine()).thenReturn(mockedMessage);

    Shot actualShot = shotReceiver.readShot(mockedReader);

    assertThat(actualShot.asInteger()).isEqualTo(shot);
  }
}