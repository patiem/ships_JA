package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It starts listening for connections on the specified port.
 *
 * @author Bartosz Pieczara
 * @version 1.5
 */
public class Server {

  private static final int PORT = 5000;
  private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

  public void runServer() {
    LOGGER.info("Server running!");

    ConnectionHandler connectionHandler = new ConnectionHandler();
    try {
      ServerSocket serverSocket = new ServerSocket(PORT);
      connectionHandler.acceptConnections(serverSocket);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }

  }
}




