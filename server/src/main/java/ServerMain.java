import communication.Server;

import java.io.IOException;

public class ServerMain {

  public static void main(String[] args) {
    Server server = new Server();
    server.runServer();
  }
}
