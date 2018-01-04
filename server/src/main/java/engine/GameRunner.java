package engine;

import communication.MessageSender;
import communication.PlayerRegistry;
import fleet.Fleet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * It creates the game events and notifies player accordingly.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */

public class GameRunner {
  private static final Logger LOGGER = Logger.getLogger(GameRunner.class.getName());

  private final Round round;
  private final PlayerRegistry playerRegistry;
  private final ShotReceiver shotReceiver = new SocketShotReceiver();
  private final Referee referee = new Referee();
  private GameState gameState = GameState.ACTIVE;

  public GameRunner(final Round round, final PlayerRegistry playerRegistry) {
    this.round = round;
    this.playerRegistry = playerRegistry;
  }

  public void runGame() {
    while (gameState == GameState.ACTIVE) {
      Shot shot = shotReceiver.readShot(playerRegistry.getCurrentReader());
      Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
      ShotResult result = round.makeShot(fleetUnderFire, shot);
      gameState = referee.isVictory(fleetUnderFire);

      sendMessage(result);

      if (result != ShotResult.HIT) {
        playerRegistry.switchPlayers();
      }

      logShotInfo(shot, result);
    }
  }

  private void sendMessage(final ShotResult result) {
    String message = result.toString();

    if (gameState == GameState.WIN) {
      message = gameState.toString();
    }

    MessageSender messageSender = new MessageSender();
    try {
      messageSender.sendMessageToPlayer(playerRegistry.getCurrentPlayer(), message);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  private void logShotInfo(final Shot shot, final ShotResult shotResult) {
    String logMessage = String.format("Player: %s, shot: position: %s, shotState: %s; gameState: %s",
        playerRegistry.currentPlayerName(), shot.asInteger(), shotResult, gameState);
    LOGGER.info(logMessage);
  }
}
