package communication;

import fleet.Fleet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class PlayerClient {

  private final BufferedReader reader;

  private final Socket socket;
  private final Fleet fleet;
  private final String playerName;

  public PlayerClient(String playerName, Socket socket, BufferedReader reader, Fleet playerFleet) {
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

  public void sendMessageToPlayer(final String message) {
    try {
      boolean autoFlush = false;
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
      PrintWriter printWriter = new PrintWriter(outputStreamWriter, autoFlush);
      BufferedWriter writer = new BufferedWriter(printWriter);

      writer.write(message);
      writer.newLine();
      writer.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public BufferedReader getReader() {
    return reader;
  }
}
