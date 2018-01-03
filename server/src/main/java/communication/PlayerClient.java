package communication;

import fleet.Fleet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It hold a player's name, selected fleet and socket used for communication.
 * @author Bartosz Pieczara / Emilia Ciastek
 * @version 1.5
 */
public class PlayerClient {
  private static final Logger LOGGER = Logger.getLogger(PlayerClient.class.getName());

  private final BufferedReader reader;
  private final Socket socket;
  private final Fleet fleet;
  private final String playerName;

  PlayerClient(String playerName, Socket socket, BufferedReader reader, Fleet playerFleet) {
    this.playerName = playerName;
    this.socket = socket;
    this.fleet = playerFleet;
    this.reader = reader;
  }

  String getName() {
    return playerName;
  }

  Fleet getFleet() {
    return fleet;
  }

  void sendMessageToPlayer(final String message) {
    try {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
      PrintWriter printWriter = new PrintWriter(outputStreamWriter, false);
      BufferedWriter writer = new BufferedWriter(printWriter);

      writer.write(message);
      writer.newLine();
      writer.flush();

    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  BufferedReader getReader() {
    return reader;
  }
}
