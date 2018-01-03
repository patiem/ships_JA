package connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

  private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
  private static final int PORT = 5000;

  private Sender out;
  private Receiver in;
  private String host;

  public void run() {
    try {
      Connector connector = SocketConnector.from(host, PORT);
      in = MessageIn.from(connector);
      out = MessageOut.from(connector);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  public void setup(String host) {
    this.host = host;
  }

  public void sendMessage(String message) {
    out.sendMessage(message);
  }

  public String getMessage() {
    return in.readMessage();
  }
}
