package engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import communication.MessageSender;
import communication.PlayerClient;
import json.JsonGeneratorAdapter;
import responses.Response;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface GameRunnerState {

  default void sendResponse(Response responseToSend, PlayerClient player) {
    Logger logger = Logger.getLogger(GameRunnerState.class.getName());
    try {
      JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();
      MessageSender messageSender = new MessageSender();

      String message = jsonGeneratorAdapter.createJson(responseToSend, new ObjectMapper());
      messageSender.sendMessageToPlayer(player, message);
      String logMessage = String.format("Message has been send: %s", message);
      logger.info(logMessage);
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
  }
  void  sendFinalResponse();
  GameRunnerState run() throws IOException;
}