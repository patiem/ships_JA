package communication;

import engine.GameRunner;
import engine.Round;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It establishes connections and creates the game object.
 * @author Bartosz Pieczara
 * @version 1.5
 */
class ConnectionHandler {
  private static final Logger LOGGER = Logger.getLogger(ConnectionHandler.class.getName());

  private PlayerTracker playerTracker = new PlayerTracker();

  void acceptConnections(final ServerSocket serverSocket) {
    try {
      acceptPlayer(serverSocket);
      acceptPlayer(serverSocket);

    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }

    createGame();
  }

  private void createGame() {
    Round round = new Round();
    GameRunner gameRunner = new GameRunner(round, playerTracker);
    gameRunner.runGame();
  }

  private void acceptPlayer(final ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();
    playerTracker.registerPlayer(socket);
  }
}
