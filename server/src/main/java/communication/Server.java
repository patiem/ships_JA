package communication;

import messages.ServerLogger;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.logging.Level;

/**
 * It starts listening for connections on the specified port.
 *
 * @author Bartosz Pieczara
 * @version 1.5
 */
public class Server {

  private ServerLogger serverLogger = ServerLogger.getInstance();
  private static final String SERVER_CONFIG_FILE = "config.properties";

  void runServer() {

    serverLogger.info("Server running!");
    try {
      ConnectionHandler connectionHandler = new ConnectionHandler();
      int portNumber = setUpServerConfig();
      ServerSocket serverSocket = new ServerSocket(portNumber);
      connectionHandler.acceptConnections(serverSocket);
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
    }
  }

  private int setUpServerConfig() throws IOException {
    Properties properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(SERVER_CONFIG_FILE);
    properties.load(config);
    return Integer.parseInt(properties.getProperty("portNumber"));
  }
}
