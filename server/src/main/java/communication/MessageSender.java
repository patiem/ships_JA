package communication;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * It sends messages to the players.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
class MessageSender {
  void send(PrintWriter printWriter, String messageToSend) {
    printWriter.println(messageToSend);
  }

  public void sendMessageToPlayer(PlayerClient playerClient, String messageToSend) {
    try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(
        playerClient.getSocket().getOutputStream(), StandardCharsets.UTF_8), true)) {
      printWriter.println(messageToSend);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void sendMessageToPlayer2(PlayerClient playerClient, String messageToSend) throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(playerClient.getSocket().getOutputStream(), StandardCharsets.UTF_8);
    PrintWriter printWriter = new PrintWriter(writer, true);
    printWriter.println(messageToSend);
    printWriter.close();
  }
}
