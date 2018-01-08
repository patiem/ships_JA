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
        "1",
        "5",
        "10"
    };
  }

  @Test(dataProvider = "shots input")
  public void givenBufferedReaderWhenReadShotThenReturnCorrectShot(String mockedInput) throws IOException {
    ShotReceiver shotReceiver = new SocketShotReceiver();
    BufferedReader mockedReader = mock(BufferedReader.class);
    when(mockedReader.readLine()).thenReturn(mockedInput);

    Shot actualShot = shotReceiver.readShot(mockedReader);
    Integer expectedValue = Integer.parseInt(mockedInput);

    assertThat(actualShot.asInteger()).isEqualTo(expectedValue);
  }

}