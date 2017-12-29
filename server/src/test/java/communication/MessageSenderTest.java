package communication;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageSenderTest {

  @DataProvider(name = "messages to send")
  public static Object[] messagesToSend() {
    return new Object[] {
        "You are connected",
        "You're first PlayerClient.",
        "Server received your fleet"
    };
  }

  @Test(dataProvider = "messages to send")
  public void shouldSendProvidedMessageToSocket(String messageToSend) {
    PrintWriter mockedPrintWriter = mock(PrintWriter.class);
    MessageSender messageSender = new MessageSender();
    int expectedInvocationNumber = 1;

    messageSender.send(mockedPrintWriter, messageToSend);

    verify(mockedPrintWriter, times(expectedInvocationNumber)).println(messageToSend);
  }
}