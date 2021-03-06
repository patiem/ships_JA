package communication;

import fleet.Fleet;

import java.net.Socket;

/**
 * It hold a player's name, selected fleet and socket used for communication.
 *
 * @author Bartosz Pieczara / Emilia Ciastek
 * @version 1.5
 */
public class PlayerClient {

  private final Socket socket;
  private final Fleet fleet;
  private final String playerName;

  PlayerClient(String playerName, Socket socket, Fleet playerFleet) {
    this.playerName = playerName;
    this.socket = socket;
    this.fleet = playerFleet;
  }

  public String getName() {
    return playerName;
  }

  Fleet getFleet() {
    return fleet;
  }

  public Socket getSocket() {
    return socket;
  }
}
