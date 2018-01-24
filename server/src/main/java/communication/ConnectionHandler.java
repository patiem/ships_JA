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
  private final Output output;

  public ConnectionHandler() throws IOException {
    output = OutputSelector.getOutput();
  }

  void acceptConnections(final ServerSocket serverSocket) throws IOException {

    acceptPlayer(serverSocket);
    acceptPlayer(serverSocket);

    setUpGame();
  }

  private void setUpGame() throws IOException {
    GameRunner gameRunner = new GameRunner(new ActiveState(playerRegistry, output));
    gameRunner.runGame();
  }

  private void acceptPlayer(final ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();
    PlayerClient playerClient = createClient(socket);

    playerRegistry.registerPlayer(playerClient);
    String transcriptMessage = "New player: %s has joined the game";
    output.transcript(String.format(transcriptMessage, playerClient.getName()));
  }

  private PlayerClient createClient(final Socket socket) throws IOException {
    MessageReceiver messageReceiver = new MessageReceiver();
    ConnectionMessage connectionMessage = messageReceiver.receiveConnectionMessage(socket);
    String playerName = connectionMessage.getName();
    Fleet playerFleet = new CustomFleet(connectionMessage.getFleetModel());
    return new PlayerClient(playerName, socket, playerFleet);
  }
}
