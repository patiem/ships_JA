package communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.GameState;
import json.JsonGeneratorAdapter;
import responses.Response;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It sends messages to the players.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class SocketMessageSender implements MessageSender {

  private final Socket socket;

  public SocketMessageSender(Socket socket) {
    this.socket = socket;
  }

  private void sendMessageToPlayer(String messageToSend) throws IOException {
    OutputStreamWriter writer = new OutputStreamWriter(
        socket.getOutputStream(), StandardCharsets.UTF_8);
    PrintWriter printWriter = new PrintWriter(writer, true);
    BufferedWriter bufferedWriter = new BufferedWriter(printWriter);
    bufferedWriter.write(messageToSend);
    bufferedWriter.newLine();
    bufferedWriter.flush();
  }

  @Override
  public void sendResponse(Response responseToSend) {
    Logger logger = Logger.getLogger(GameState.class.getName());
    try {
      JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();

      String message = jsonGeneratorAdapter.createJson(responseToSend, new ObjectMapper());
      sendMessageToPlayer(message);
      String logMessage = String.format("Message has been send: %s", message);
      logger.info(logMessage);
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
  }
}
