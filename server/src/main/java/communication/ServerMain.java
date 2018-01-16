package communication;

/**
 * This class launches the server.
 *
 * @author Bartosz Pieczara
 * @version 1.5
 */

class ServerMain {

  public static void main(String[] args) {
    Server server = new Server();
    server.runServer();
  }
}
