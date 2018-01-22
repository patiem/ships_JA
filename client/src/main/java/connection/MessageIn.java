package connection;

import messages.ClientLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It is responsible for receiving messages from the server.
 *
 * @version 1.5
 */
public class MessageIn implements Receiver {

  private final ClientLogger LOGGER = ClientLogger.getInstance();
  private final BufferedReader scanner;

  static MessageIn from(Connector connector) throws IOException {
    return new MessageIn(new BufferedReader(
        new InputStreamReader(connector.getInStream(), StandardCharsets.UTF_8)));
  }

  private MessageIn(BufferedReader scanner) {
    this.scanner = scanner;
  }

  @Override
  public String readMessage() {
    try {
      String message = scanner.readLine();
      LOGGER.log(Level.INFO, message);
      return message;
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());

    }
    return "";
  }
}
