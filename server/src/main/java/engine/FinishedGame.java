package engine;

import communication.MessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import messages.ServerLogger;
import responses.LossResponse;
import responses.WinResponse;

import java.io.IOException;

public class FinishedGame implements GameState {


  private final MessageSender messageSender;

  private PlayerRegistry playerRegistry;
  private boolean isGameRunning = true;
  private ServerLogger serverLogger = ServerLogger.getInstance();

  FinishedGame(PlayerRegistry playerRegistry, MessageSender messageSender) {
    this.playerRegistry = playerRegistry;
    this.messageSender = messageSender;
  }

  @Override
  public GameState run() throws IOException {
    PlayerClient winner = playerRegistry.getCurrentPlayer();
    PlayerClient looser = playerRegistry.getWaitingPlayer();

    messageSender.sendResponse(new WinResponse(), winner);
    messageSender.sendResponse(new LossResponse(), looser);
    String logMessage = String.format("Message has been send. Player %s won, player %s lost",
        winner, looser);

    serverLogger.info(logMessage);
    serverLogger.getFileHandler().close();
    isGameRunning = false;
    return this;
  }

  @Override
  public boolean isGameRunning() {
    return isGameRunning;
  }
}
