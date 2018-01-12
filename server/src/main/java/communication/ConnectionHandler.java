package communication;

import engine.ActiveGame;
import engine.GameRunner;
import fleet.CustomFleet;
import fleet.Fleet;
import messages.ConnectionMessage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * It establishes connections and creates the game object.
 *
 * @author Bartosz Pieczara
 * @version 1.5
 */
class ConnectionHandler {
  private final PlayerRegistry playerRegistry = new PlayerRegistry();

  void acceptConnections(final ServerSocket serverSocket) throws IOException {
    acceptPlayer(serverSocket);
    acceptPlayer(serverSocket);
    setUpGame();
  }

  private void setUpGame() throws IOException {
    GameRunner gameRunner = new GameRunner(playerRegistry, new ActiveGame(playerRegistry));
    gameRunner.runGame();
  }

  private void acceptPlayer(final ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();
    PlayerClient playerClient = createClient(socket);
    playerRegistry.registerPlayer(playerClient);
  }

  private PlayerClient createClient(final Socket socket) throws IOException {
    MessageReceiver messageReceiver = new MessageReceiver();
    ConnectionMessage connectionMessage = messageReceiver.receiveConnectionMessage(socket);
    String playerName = connectionMessage.getName();
    Fleet playerFleet = new CustomFleet(connectionMessage.getFleetModel());
    return new PlayerClient(playerName, socket, playerFleet);
  }
}
