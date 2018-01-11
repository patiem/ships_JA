package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It receives messages from the players.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class MessageReceiver {
  private static final Logger LOGGER = Logger.getLogger(MessageReceiver.class.getName());

  public String receiveMessage(final BufferedReader bufferedReader) {
    try {
      return bufferedReader.readLine();
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
    return "";
  }
}
