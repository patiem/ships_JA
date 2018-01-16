package engine;

import communication.MessageReceiver;
import communication.PlayerRegistry;
import fleet.Fleet;
import messages.ShotMessage;
import model.Shot;
import responses.HitResponse;
import responses.MissedResponse;
import responses.OpponentHitResponse;
import responses.OpponentMissedResponse;
import responses.PlayResponse;
import responses.SunkResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class ActiveGame implements GameRunnerState {
  private static final Logger LOGGER = Logger.getLogger(ActiveGame.class.getName());
  private final Round round = new Round();
  private PlayerRegistry playerRegistry;
  private final Referee referee = new Referee();

  public ActiveGame(PlayerRegistry playerRegistry) {
    this.playerRegistry = playerRegistry;
  }

  @Override
  public void sendFinalResponse() {
    throw new UnsupportedOperationException();
  }

  @Override
  public GameRunnerState run() throws IOException {

    while (true) {
      MessageReceiver messageReceiver = new MessageReceiver();
      ShotMessage shotMessage = messageReceiver.receiveShotMessage(playerRegistry.getCurrentPlayer().getSocket());
      Shot shot = shotMessage.getShot();
      Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
      ShotResult result = round.fireShot(fleetUnderFire, shot);
      logShotInfo(shot, result);

      if (result == ShotResult.SUNK) {
        sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new SunkResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
        if (referee.isVictory(fleetUnderFire) == GameState.WIN) {
          return new FinishedGame(playerRegistry);
        }
      } else if (result == ShotResult.HIT) {
        sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
        if (referee.isVictory(fleetUnderFire) == GameState.WIN) {
          return new FinishedGame(playerRegistry);
        }
      } else {
        sendResponse(new MissedResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new OpponentMissedResponse(shot), playerRegistry.getWaitingPlayer());
        playerRegistry.switchPlayers();
        sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());
      }
    }
  }

  private void logShotInfo(final Shot shot, final ShotResult shotResult) {
    String messageTemplate = "Player: %s, shot: position: %s, shotState: %s;";
    String logMessage = String.format(messageTemplate, playerRegistry.currentPlayerName(),
        shot.asInteger(), shotResult);
    LOGGER.info(logMessage);
  }
}
