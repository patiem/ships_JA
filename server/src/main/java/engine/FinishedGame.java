package engine;


import com.fasterxml.jackson.databind.ObjectMapper;
import communication.MessageSender;
import communication.PlayerRegistry;
import json.JsonGeneratorAdapter;
import responses.LossResponse;
import responses.WinResponse;
import java.io.IOException;
import java.util.logging.Level;

class FinishedGame implements GameRunnerState {

  private PlayerRegistry playerRegistry;

  FinishedGame(PlayerRegistry playerRegistry) {
    this.playerRegistry = playerRegistry;
  }

  @Override
  public void sendResponse() {
    try {
      JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();
      MessageSender messageSender = new MessageSender();

      String winMessage = jsonGeneratorAdapter.createJson(new WinResponse(), new ObjectMapper());
      String lossMessage = jsonGeneratorAdapter.createJson(new LossResponse(), new ObjectMapper());
      messageSender.sendMessageToPlayer(playerRegistry.getCurrentPlayer(), winMessage);
      messageSender.sendMessageToPlayer(playerRegistry.getWaitingPlayer(), lossMessage);

      String logMessage = String.format("Message has been send: %s", winMessage);
      LOGGER.info(logMessage);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  @Override
  public GameRunnerState run() {
   return new FinishedGame(playerRegistry);
  }

}
