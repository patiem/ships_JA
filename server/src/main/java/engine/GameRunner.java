package engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import communication.MessageReceiver;
import communication.MessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import fleet.Fleet;
import json.JsonGeneratorAdapter;
import messages.ShotMessage;
import model.Shot;
import responses.HitResponse;
import responses.LossResponse;
import responses.MissedResponse;
import responses.OpponentHitResponse;
import responses.OpponentMissedResponse;
import responses.PlayResponse;
import responses.Response;
import responses.WinResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * It creates the game events and notifies player accordingly.
 *
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

  public void runGame() throws IOException {
    sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());

    while (gameState == GameState.ACTIVE) {
      MessageReceiver messageReceiver = new MessageReceiver();
      ShotMessage shotMessage = messageReceiver.receiveShotMessage(playerRegistry.getCurrentPlayer().getSocket());
      Shot shot = shotMessage.getShot();
      Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
      ShotResult result = round.fireShot(fleetUnderFire, shot);
      logShotInfo(shot, result);

      if (result == ShotResult.HIT) {
        sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
        gameState = referee.isVictory(fleetUnderFire);
      } else {
        sendResponse(new MissedResponse(), playerRegistry.getCurrentPlayer());
        sendResponse(new OpponentMissedResponse(shot), playerRegistry.getWaitingPlayer());
        playerRegistry.switchPlayers();
        sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());
      }
    }

    sendResponse(new WinResponse(), playerRegistry.getCurrentPlayer());
    sendResponse(new LossResponse(), playerRegistry.getWaitingPlayer());
  }

  private void logShotInfo(final Shot shot, final ShotResult shotResult) {
    String messageTemplate = "Player: %s, shot: position: %s, shotState: %s; gameState: %s";
    String logMessage = String.format(messageTemplate, playerRegistry.currentPlayerName(),
        shot.asInteger(), shotResult, gameState);
    LOGGER.info(logMessage);
  }

  private void sendResponse(Response responseToSend, PlayerClient player) {
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
}
