package communication;

import fleet.Fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It adds players to the game.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class PlayerRegistry {
  private static final Logger LOGGER = Logger.getLogger(PlayerRegistry.class.getName());
  private final Deque<PlayerClient> players = new ArrayDeque<>();

  void registerPlayer(PlayerClient newPlayer) {
    //final String playerIsConnected = "CON";
    addPlayer(newPlayer);
    LOGGER.info("PlayerClient added: " + newPlayer.getName());

/*
    try {
      MessageSender messageSender = new MessageSender();
      messageSender.sendMessageToPlayer(newPlayer, playerIsConnected);
      LOGGER.info("PlayerClient added: " + newPlayer.getName());
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
    */
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

  public PlayerClient getCurrentPlayer() {
    return players.peekFirst();
  }
}
