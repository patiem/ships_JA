package connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fields.Field;
import json.JsonGeneratorAdapter;
import messages.ClientLogger;
import messages.ShotMessage;
import model.Shot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

/**
 * It handles connection with server.
 *
 * @version 1.5
 */
public class Client {

  private final ClientLogger LOGGER = ClientLogger.getInstance();
  private static final String SERVER_CONFIG_FILE = "config.properties";

  private Sender out;
  private Receiver in;

  /**
   * Allows the client to receive and send messages to the server.
   */
  public void run() {
    try {
      String[] configValues = setUpGame();
      int port = Integer.parseInt(configValues[1]);
      String host = String.valueOf(configValues[0]);
      Connector connector = SocketConnector.from(host, port);
      in = MessageIn.from(connector);
      out = MessageOut.from(connector);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * It reads configuration of server from file and sets the port and IP.
   */
  private String[] setUpGame() throws IOException {
    Properties properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(SERVER_CONFIG_FILE);
    properties.load(config);
    String[] configValues = new String[2];
    configValues[0] = properties.getProperty("IP");
    configValues[1] = properties.getProperty("portNumber");
    return configValues;
  }

  /**
   * It sends messages to the server.
   *
   * @param message - the message that needs to be sent to the server.
   */
  void sendMessage(String message) {
    out.sendMessage(message);
  }

  /**
   * It sends a particular position to the server.
   *
   * @param field - the specific position
   */
  public void sendMessage(Field field) {
    Shot shotToSend = new Shot(field.positionAsInteger());
    JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();
    try {
      String message = jsonGeneratorAdapter.createJson(
          new ShotMessage(shotToSend), new ObjectMapper());
      sendMessage(message);
      String logMessage = String.format("Message has benn send: %s", message);
      LOGGER.info(logMessage);
    } catch (JsonProcessingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * It receives messages from the server.
   */
  public String getMessage() {
    return in.readMessage();
  }
}
