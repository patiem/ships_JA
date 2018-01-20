package engine;

import communication.*;
import fleet.Fleet;
import messages.ShotMessage;
import model.Shot;
import responses.PlayResponse;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ActiveState implements GameState {
  private static final Logger LOGGER = Logger.getLogger(ActiveState.class.getName());
  private final Round round = new Round();
  private final PlayerRegistry playerRegistry;

  public ActiveState(PlayerRegistry playerRegistry) {
    this.playerRegistry = playerRegistry;
  }

  @Override
  public GameState run() throws IOException {
    PlayerClient currentPlayer = playerRegistry.getCurrentPlayer();
    currentPlayer.sendResponse(new PlayResponse());

    ShotMessage shotMessage = currentPlayer.receiveShotMessage();
    Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
    Shot shot = shotMessage.getShot();
    ShotResult result = round.fireShotFixed(fleetUnderFire, shot);
    logShotInfo(shot, result);

    result.notifyClients(playerRegistry, shot);

    if(fleetUnderFire.isSunk()){
      return new FinishedGame(playerRegistry);
    }

    return this;
  }

  private void logShotInfo(final Shot shot, final ShotResult shotResult) {
    String messageTemplate = "Player: %s, shot: position: %s, shotState: %s;";
    String logMessage = String.format(messageTemplate, playerRegistry.currentPlayerName(),
        shot.asInteger(), shotResult.toString());
    LOGGER.info(logMessage);
  }
}
