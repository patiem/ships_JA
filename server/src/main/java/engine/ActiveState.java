package engine;

import communication.MessageReceiver;
import communication.MessageSender;
import communication.Output;
import communication.PlayerRegistry;
import communication.SocketMessageSender;
import fleet.Fleet;
import messages.ServerLogger;
import messages.ShotMessage;
import common.model.Shot;
import responses.PlayResponse;

import java.io.IOException;
import java.net.Socket;

public class ActiveState implements GameState {
  private ServerLogger serverLogger = ServerLogger.getInstance();
  private final Round round = new Round();
  private final PlayerRegistry playerRegistry;
  private final Output output;
  private MessageReceiver messageReceiver = new MessageReceiver();

  public ActiveState(PlayerRegistry playerRegistry, Output output) {
    this.playerRegistry = playerRegistry;
    this.output = output;
  }

  @Override
  public GameState run() throws IOException {
    MessageSender messageSender = new SocketMessageSender();
    messageSender.sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());

    Socket socket = playerRegistry.getCurrentPlayer().getSocket();
    ShotMessage shotMessage = messageReceiver.receiveShotMessage(socket);
    Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
    Shot shot = shotMessage.getShot();
    ShotResult result = round.fireShot(fleetUnderFire, shot);
    logShotInfo(shot, result);
    output.writeMessage("Player: " + playerRegistry.currentPlayerName() + " fired a shot: " + shot.asInteger() + " ; it's a " + result.toString());
    result.notifyClients(playerRegistry, shot);

    if (fleetUnderFire.isSunk()) {
      return new FinishedGame(playerRegistry, new SocketMessageSender(), output);
    }

    return this;
  }

  private void logShotInfo(final Shot shot, final ShotResult shotResult) {
    String messageTemplate = "Player: %s, shot: position: %s, shotState: %s;";
    String logMessage = String.format(messageTemplate, playerRegistry.currentPlayerName(),
        shot.asInteger(), shotResult.toString());
    serverLogger.info(logMessage);
  }
}
