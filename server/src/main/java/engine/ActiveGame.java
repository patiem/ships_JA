package engine;

import communication.MessageReceiver;
import communication.PlayerRegistry;
import fleet.Fleet;
import messages.ServerLogger;
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

public class ActiveGame implements GameRunnerState {

  private ServerLogger serverLogger = ServerLogger.getInstance();
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
      Socket socket = playerRegistry.getCurrentPlayer().getSocket();
      ShotMessage shotMessage = messageReceiver.receiveShotMessage(socket);
      Shot shot = shotMessage.getShot();
      Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
      ShotResult result = round.fireShot(fleetUnderFire, shot);
      serverLogger.logShotInfo(shot.toString(), result.name(), playerRegistry.currentPlayerName().toString());

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

}
