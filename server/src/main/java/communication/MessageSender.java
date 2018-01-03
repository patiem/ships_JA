package communication;

import java.io.PrintWriter;

/**
 * It sends messages to the players.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
class MessageSender {
  void send(PrintWriter printWriter, String messageToSend) {
    printWriter.println(messageToSend);
  }
}
