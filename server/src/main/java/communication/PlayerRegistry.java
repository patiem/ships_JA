package communication;

import fleet.Fleet;
import messages.ServerLogger;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * It adds players to the game.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class PlayerRegistry {

  private final Deque<PlayerClient> players = new ArrayDeque<>();

  void registerPlayer(PlayerClient newPlayer) {
    addPlayer(newPlayer);
  }

  private void addPlayer(PlayerClient playerClient) {
    players.add(playerClient);
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

  public PlayerClient getWaitingPlayer() {
    return players.peekLast();
  }
}
