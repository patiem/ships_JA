package communication;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageReceiver {

  public String receiveMessage(final BufferedReader bufferedReader) {
    try {
      return bufferedReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
