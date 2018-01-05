package communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.GameRunner;
import engine.Round;
import fleet.CustomFleet;
import fleet.Fleet;
import json.InitMessage;
import json.JsonParserAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * It establishes connections and creates the game object.
 *
 * @author Bartosz Pieczara
 * @version 1.5
 */
class ConnectionHandler {
  private static final Logger LOGGER = Logger.getLogger(ConnectionHandler.class.getName());

  private final PlayerRegistry playerRegistry = new PlayerRegistry();


  void acceptConnections(final ServerSocket serverSocket) throws IOException {
    acceptPlayer(serverSocket);
    acceptPlayer(serverSocket);
    setUpGameConfiguration();
  }

  private void setUpGameConfiguration() {
    Round round = new Round();
    GameRunner gameRunner = new GameRunner(round, playerRegistry);
    gameRunner.runGame();
  }

  private void acceptPlayer(final ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();
    PlayerClient playerClient = createClient(socket);
    playerRegistry.registerPlayer(playerClient);
    sendConnectionConfirmationMessage(playerClient);
  }

  private PlayerClient createClient(final Socket socket) throws IOException {
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
    InitMessage initMessage = prepareJSONMessage(reader);
    Fleet playerFleet = new CustomFleet(initMessage.getFleetModel());
    return new PlayerClient(initMessage.getName(), socket, reader, playerFleet);
  }

  private void sendConnectionConfirmationMessage(PlayerClient playerClient) throws IOException {
    final String playerIsConnected = "CON";
    MessageSender messageSender = new MessageSender();
    messageSender.sendMessageToPlayer(playerClient, playerIsConnected);
  }

  private InitMessage prepareJSONMessage(BufferedReader reader) throws IOException {
    MessageReceiver messageReceiver = new MessageReceiver();
    String gameStartingObjectAsString = messageReceiver.receiveMessage(reader);
    LOGGER.info(gameStartingObjectAsString);
    JsonParserAdapter jsonParser = new JsonParserAdapter();
    InitMessage initMessage = jsonParser.parse(gameStartingObjectAsString, InitMessage.class, new ObjectMapper());
    return initMessage;
  }

}
