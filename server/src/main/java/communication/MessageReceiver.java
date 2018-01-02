package communication;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * It receives messages from the players.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
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
