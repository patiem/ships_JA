package communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import fleet.CustomFleet;
import fleet.Fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;

import json.JsonParserAdapter;
import json.InitMessage;

/**
 * It adds players to the game.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class PlayerTracker {

  private Deque<PlayerClient> players = new ArrayDeque<>();
  private MessageReceiver messageReceiver = new MessageReceiver();

  void registerPlayer(Socket socket) {
    final String playerIsConnected = "CON";

    try {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

      String gameStartingObjectAsString = messageReceiver.receiveMessage(reader);
      System.out.println(gameStartingObjectAsString);
      JsonParserAdapter jsonParser = new JsonParserAdapter();
      InitMessage initMessage = jsonParser.parse(gameStartingObjectAsString, InitMessage.class, new ObjectMapper());
      Fleet playerFleet = new CustomFleet(initMessage.getFleetModel());
      PlayerClient playerClient = new PlayerClient(initMessage.getName(), socket, reader, playerFleet);

      addPlayer(playerClient);
      playerClient.sendMessageToPlayer(playerIsConnected);
      System.out.println("PlayerClient added: " + playerClient.getName());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void sendMessageToCurrentPlayer(String message) {
    players.peekFirst().sendMessageToPlayer(message);
  }

  private void addPlayer(PlayerClient playerClient) {
    players.add(playerClient);
  }

  public BufferedReader getCurrentReader() {
    return players.peekFirst().getReader();
  }

  public Fleet getFleetUnderFire() {
    return players.peekLast().getFleet();
  }

  public void switchPlayers() {
    PlayerClient current = players.pollFirst();
    players.addLast(current);
  }

  public String currentPlayerName() {
    return players.peekFirst().getName();
  }
}


