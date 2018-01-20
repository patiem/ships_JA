package engine;

import communication.MessageReceiver;
import communication.SocketMessageSender;
import communication.PlayerRegistry;
import fleet.Fleet;
import messages.ShotMessage;
import model.Shot;
import responses.PlayResponse;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ActiveGame implements GameRunnerState {
  private static final Logger LOGGER = Logger.getLogger(ActiveGame.class.getName());
  private final Round round = new Round();
  private final PlayerRegistry playerRegistry;
  private final Referee referee = new Referee();
  private MessageReceiver messageReceiver = new MessageReceiver();

  public ActiveGame(PlayerRegistry playerRegistry) {
    this.playerRegistry = playerRegistry;
  }

  public GameRunnerState runFixed() throws IOException {
    sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());
    Socket socket = playerRegistry.getCurrentPlayer().getSocket();
    ShotMessage shotMessage = messageReceiver.receiveShotMessage(socket);
    Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
    Shot shot = shotMessage.getShot();
    IShotResult result = round.fireShotFixed(fleetUnderFire, shot);
    //logShotInfo(shot, result);

    result.notifyClients(playerRegistry, shot);

    if (referee.isVictory(fleetUnderFire) == GameState.WIN) {
      return new FinishedGame(playerRegistry, new SocketMessageSender());
    }
    return this;
  }

  private void logShotInfo(final Shot shot, final IShotResult shotResult) {
    String messageTemplate = "Player: %s, shot: position: %s, shotState: %s;";
    String logMessage = String.format(messageTemplate, playerRegistry.currentPlayerName(),
        shot.asInteger(), shotResult.toString());
    LOGGER.info(logMessage);
  }
}
