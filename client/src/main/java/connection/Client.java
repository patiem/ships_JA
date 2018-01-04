package connection;

import gui.fields.Field;

import java.io.IOException;
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
  private static final int PORT = 5000;

  private Sender out;
  private Receiver in;
  private String host;

  /**
   * Allows the client to receive and send messages to the server.
   */
  public void run() {
    try {
      Connector connector = SocketConnector.from(host, PORT);
      in = MessageIn.from(connector);
      out = MessageOut.from(connector);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
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
  public void sendMessage(String message) {
    out.sendMessage(message);
  }


  /**
   * It sends a particular position to the server.
   *
   * @param field - the specific position
   */
  public void sendMessage(Field field) {
    sendMessage(field.positionAsInteger().toString());
  }


  /**
   * It receives messages from the server.
   */
  public String getMessage() {
    return in.readMessage();
  }
}
