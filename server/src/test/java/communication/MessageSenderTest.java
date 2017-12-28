package communication;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class MessageSenderTest {

  @DataProvider(name = "messages to send")
  public static Object[] messagesToSend() {
    return new Object[] {
        LanguageVersion.CONFIRM_CONNECTION,
        LanguageVersion.WAIT_FOR_SECOND_PLAYER,
        LanguageVersion.FLEET_RECEIVED
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