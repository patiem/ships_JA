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
import java.net.Socket;
import java.util.logging.Logger;

public class ActiveGame implements GameRunnerState {
  private static final Logger LOGGER = Logger.getLogger(ActiveGame.class.getName());
  private final Round round = new Round();
  private PlayerRegistry playerRegistry;
  private final Referee referee = new Referee();
  private MessageReceiver messageReceiver = new MessageReceiver();

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
      Socket socket = playerRegistry.getCurrentPlayer().getSocket();
      ShotMessage shotMessage = messageReceiver.receiveShotMessage(socket);
      Shot shot = shotMessage.getShot();
      Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
      ShotResult result = round.fireShot(fleetUnderFire, shot);
      logShotInfo(shot, result);

      if (result == ShotResult.SUNK) {
        sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new SunkResponse(fleetUnderFire.getShipByPosition(
            shot.asInteger())), playerRegistry.getCurrentPlayer());
        sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
        if (referee.isVictory(fleetUnderFire) == GameState.WIN) {
          return new FinishedGame(playerRegistry);
        }
      } else if (result == ShotResult.HIT) {
        sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
      } else {
        sendResponse(new MissedResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new OpponentMissedResponse(shot), playerRegistry.getWaitingPlayer());
        playerRegistry.switchPlayers();
        sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());
      }
    }
  }

  public GameRunnerState runFixed() throws IOException {
    sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());
    Socket socket = playerRegistry.getCurrentPlayer().getSocket();
    ShotMessage shotMessage = messageReceiver.receiveShotMessage(socket);
    Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
    Shot shot = shotMessage.getShot();
    ShotResult result = round.fireShot(fleetUnderFire, shot);
    logShotInfo(shot, result);


    result.sendResponses(playerRegistry, shot);

    if (referee.isVictory(fleetUnderFire) == GameState.WIN) {
      return new FinishedGame(playerRegistry);
    }
    return this;
  }

  private void logShotInfo(final Shot shot, final ShotResult shotResult) {
    String messageTemplate = "Player: %s, shot: position: %s, shotState: %s;";
    String logMessage = String.format(messageTemplate, playerRegistry.currentPlayerName(),
        shot.asInteger(), shotResult);
    LOGGER.info(logMessage);
  }
}
