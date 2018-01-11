package communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.GameRunner;
import engine.Round;
import fleet.CustomFleet;
import fleet.Fleet;

import messages.ConnectionMessage;
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
    setUpGame();
  }

  private void setUpGame() throws IOException {
    Round round = new Round();
    GameRunner gameRunner = new GameRunner(round, playerRegistry);
    gameRunner.runGame();
  }

  private void acceptPlayer(final ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();
    PlayerClient playerClient = createClient(socket);
    playerRegistry.registerPlayer(playerClient);
  }

  private PlayerClient createClient(final Socket socket) throws IOException {
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
    ConnectionMessage connectionMessage = prepareJSONMessage(reader);
    Fleet playerFleet = new CustomFleet(connectionMessage.getFleetModel());
    return new PlayerClient(connectionMessage.getName(), socket, reader, playerFleet);
  }

  private ConnectionMessage prepareJSONMessage(BufferedReader reader) throws IOException { //TODO: what this method is doing -> better name?
    MessageReceiver messageReceiver = new MessageReceiver();
    String gameStartingObjectAsString = messageReceiver.receiveMessage(reader);
    LOGGER.info(gameStartingObjectAsString);
    JsonParserAdapter jsonParser = new JsonParserAdapter();
    ConnectionMessage connectionMessage = jsonParser.parse(gameStartingObjectAsString, ConnectionMessage.class, new ObjectMapper());
    return connectionMessage;
  }

}
