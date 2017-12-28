package communication;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class MessageReceiverTest {

  @DataProvider(name = "messages to receive")
  public static Object[] messagesToReceived() {
    return new Object[] {
        "one message",
        "another message",
        "and one more message"
    };
  }

  @Test(dataProvider = "messages to receive")
  public void shouldReceiveMessageFromProvidedSocket(String messageToReceive) {
    BufferedReader mockedInput = mock(BufferedReader.class);
    try {
      when(mockedInput.readLine()).thenReturn(messageToReceive);
    } catch (IOException e) {
      e.printStackTrace();
    }

    MessageReceiver messageReceiver = new MessageReceiver();

    String actualReceivedMessage = messageReceiver.receiveMessage(mockedInput);

    assertEquals(actualReceivedMessage, messageToReceive);
  }
}