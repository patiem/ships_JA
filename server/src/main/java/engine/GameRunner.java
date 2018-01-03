package engine;

import communication.PlayerTracker;
import fleet.Fleet;
import java.util.logging.Logger;


/**
 * It creates the game events and notifies player accordingly.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */

public class GameRunner {
  private static final Logger LOGGER = Logger.getLogger(GameRunner.class.getName());

  private final Round round;
  private final PlayerTracker playerTracker;
  private final ShotReceiver shotReceiver = new SocketShotReceiver();
  private final Referee referee = new Referee();
  private GameState gameState = GameState.ACTIVE;

  public GameRunner(final Round round, final PlayerTracker playerTracker) {
    this.round = round;
    this.playerTracker = playerTracker;
  }

  public void runGame() {
    while (gameState == GameState.ACTIVE) {
      Shot shot = shotReceiver.readShot(playerTracker.getCurrentReader());
      Fleet fleetUnderFire = playerTracker.getFleetUnderFire();
      ShotResult result = round.makeShot(fleetUnderFire, shot);
      gameState = referee.isVictory(fleetUnderFire);

      sendMessage(result);

      if (result != ShotResult.HIT) {
        playerTracker.switchPlayers();
      }

      logShotInfo(shot, result);
    }
  }

  private void sendMessage(final ShotResult result) {
    String message = result.toString();

    if (gameState == GameState.WIN) {
      message = gameState.toString();
    }

    playerTracker.sendMessageToCurrentPlayer(message);
  }

  private void logShotInfo(final Shot shot, final ShotResult shotResult) {
    String logMessage = String.format("Player: %s, shot: position: %s, shotState: %s; gameState: %s",
        playerTracker.currentPlayerName(), shot.asInteger(), shotResult, gameState);
    LOGGER.info(logMessage);
  }
}
