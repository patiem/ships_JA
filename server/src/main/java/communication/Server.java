package communication;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It starts listening for connections on the specified port.
 *
 * @author Bartosz Pieczara
 * @version 1.5
 */
public class Server {

  private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
  private  final String fileName ="config.properties";

  public void runServer() {
    LOGGER.info("Server running!");

    ConnectionHandler connectionHandler = new ConnectionHandler();
    try {
        int portNumber = setUpServerConfig();
      ServerSocket serverSocket = new ServerSocket(portNumber);
      connectionHandler.acceptConnections(serverSocket);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }

  }

  private int setUpServerConfig() throws IOException{
      Properties properties = new Properties();
      InputStream config = ClassLoader.getSystemResourceAsStream(fileName);
      properties.load(config);
      return Integer.parseInt(properties.getProperty("portNumber"));
  }
}




