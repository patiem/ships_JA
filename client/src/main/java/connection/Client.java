package connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fields.Field;
import json.JsonGeneratorAdapter;
import messages.ShotMessage;
import model.Shot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It sends and receives messages from the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class Client {

  private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
  private static final String SERVER_CONFIG_FILE = "config.properties";

  private Sender out;
  private Receiver in;
  private String host;

  /**
   * Allows the client to receive and send messages to the server.
   */
  public void run() {
    try {
      int port = setUpPort();
      Connector connector = SocketConnector.from(host, port);
      in = MessageIn.from(connector);
      out = MessageOut.from(connector);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  private int setUpPort() throws IOException {
    Properties properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(SERVER_CONFIG_FILE);
    properties.load(config);
    return Integer.parseInt(properties.getProperty("portNumber"));
  }

  /**
   * It sets the hostname.
   *
   * @param host - the hostname
   */
  public void setup(String host) {
    this.host = host;
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
