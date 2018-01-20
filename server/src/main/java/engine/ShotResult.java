package engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import communication.MessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import fleet.Fleet;
import json.JsonGeneratorAdapter;
import model.Shot;
import responses.HitResponse;
import responses.MissedResponse;
import responses.OpponentHitResponse;
import responses.OpponentMissedResponse;
import responses.Response;
import responses.SunkResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It holds shot result states.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public enum ShotResult {
  HIT {
    public void sendResponses(PlayerRegistry playerRegistry, Shot shot) {
      MESSAGE_SENDER.sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
      MESSAGE_SENDER.sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
    }
  },
  MISSED {
    public void sendResponses(PlayerRegistry playerRegistry, Shot shot) {
      MESSAGE_SENDER.sendResponse(new MissedResponse(), playerRegistry.getCurrentPlayer());
      MESSAGE_SENDER.sendResponse(new OpponentMissedResponse(shot), playerRegistry.getWaitingPlayer());
      playerRegistry.switchPlayers();
    }
  },
  SUNK {
    public void sendResponses(PlayerRegistry playerRegistry, Shot shot) {
      Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
      MESSAGE_SENDER.sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
      MESSAGE_SENDER.sendResponse(new SunkResponse(fleetUnderFire.getShipByPosition(
          shot.asInteger())), playerRegistry.getCurrentPlayer());
      MESSAGE_SENDER.sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
    }
  };

  private static final MessageSender MESSAGE_SENDER = new MessageSender();

  public abstract void sendResponses(PlayerRegistry playerRegistry, Shot shot);


//  private static void sendResponse(Response responseToSend, PlayerClient player) {
//    Logger logger = Logger.getLogger(GameRunnerState.class.getName());
//    try {
//      JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();
//      MessageSender messageSender = new MessageSender();
//
//      String message = jsonGeneratorAdapter.createJson(responseToSend, new ObjectMapper());
//      messageSender.sendMessageToPlayer(player, message);
//      String logMessage = String.format("Message has been send: %s", message);
//      logger.info(logMessage);
//    } catch (IOException e) {
//      logger.log(Level.SEVERE, e.getMessage());
//    }
//  }

}
