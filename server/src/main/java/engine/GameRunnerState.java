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
  Logger LOGGER = Logger.getLogger(ActiveGame.class.getName());

  default void sendResponse(Response responseToSend, PlayerClient player) {
    try {
      JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();
      MessageSender messageSender = new MessageSender();

      String message = jsonGeneratorAdapter.createJson(responseToSend, new ObjectMapper());
      messageSender.sendMessageToPlayer(player, message);
      String logMessage = String.format("Message has been send: %s", message);
      LOGGER.info(logMessage);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  default void sendResponse(){ }

  default GameRunnerState run(){
    return null;
  }

}
