package communication;

import java.io.IOException;
import java.net.ServerSocket;


/**
 * It starts listening for connections on the specified port.
 * @author Bartosz Pieczara
 * @version 1.5
 */
public class Server {

  private static final int PORT = 5000;

  public void runServer() {
    System.out.println("Server running ! ");

    ConnectionHandler connectionHandler = new ConnectionHandler();
    try {
      ServerSocket serverSocket = new ServerSocket(PORT);
      connectionHandler.acceptConnections(serverSocket);
    } catch (IOException e) {
      e.printStackTrace();
      //Loger
    }
  }
}




