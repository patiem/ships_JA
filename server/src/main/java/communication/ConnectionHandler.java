package communication;

import engine.ActiveState;
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
    GameRunner gameRunner = new GameRunner(new ActiveState(playerRegistry));
    gameRunner.runGameFixed();
  }

  private void acceptPlayer(final ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();

    MessageReceiver messageReceiver = new SocketMessageReceiver(socket);
    MessageSender messageSender = new SocketMessageSender(socket);
    ClientCreator clientCreator = new ClientCreator(messageReceiver, messageSender);
    PlayerClient playerClient = clientCreator.createClient();
    playerRegistry.registerPlayer(playerClient);
  }
}
