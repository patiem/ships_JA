import communication.Server;

import java.io.IOException;

/**
 * This class launches the server.
 * @author Bartosz Pieczara
 * @version 1.5
 */
public class ServerMain {

  public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.runServer();
  }
}
