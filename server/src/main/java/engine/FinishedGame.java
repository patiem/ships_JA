package engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import communication.MessageSender;
import communication.PlayerRegistry;
import json.JsonGeneratorAdapter;
import messages.ServerLogger;
import responses.LossResponse;
import responses.WinResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinishedGame implements GameRunnerState {

  private PlayerRegistry playerRegistry;
  private ServerLogger serverLogger = ServerLogger.getInstance();

  FinishedGame(PlayerRegistry playerRegistry) {
    this.playerRegistry = playerRegistry;
  }

  @Override
  public void sendFinalResponse() {

    try {
      JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();
      MessageSender messageSender = new MessageSender();

      String winMessage = jsonGeneratorAdapter.createJson(new WinResponse(), new ObjectMapper());
      String lossMessage = jsonGeneratorAdapter.createJson(new LossResponse(), new ObjectMapper());
      messageSender.sendMessageToPlayer(playerRegistry.getCurrentPlayer(), winMessage);
      messageSender.sendMessageToPlayer(playerRegistry.getWaitingPlayer(), lossMessage);

      String logMessage = String.format("Message has been send: %s", winMessage);
      String logMessage2 = String.format("Message has been send: %s", lossMessage);
      serverLogger.info(logMessage);
      serverLogger.info(logMessage2);
      serverLogger.getFileHandler().close();
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
    }
  }

  @Override
  public GameRunnerState run() {
    return new FinishedGame(playerRegistry);
  }
}
