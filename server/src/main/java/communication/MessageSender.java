package communication;

import java.io.BufferedWriter;
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
public class MessageSender {

  public void sendMessageToPlayer(PlayerClient playerClient,
                                  String messageToSend) throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(
        playerClient.getSocket().getOutputStream(), StandardCharsets.UTF_8);
    PrintWriter printWriter = new PrintWriter(writer, true);
    BufferedWriter bufferedWriter = new BufferedWriter(printWriter);
    bufferedWriter.write(messageToSend);
    bufferedWriter.newLine();
    bufferedWriter.flush();
  }
}
