package engine;

import communication.PlayerRegistry;
import communication.SocketMessageSender;
import model.Shot;
import responses.HitResponse;
import responses.OpponentHitResponse;

public class ShipHit implements ShotResult {
  private final SocketMessageSender messageSender;

  ShipHit(SocketMessageSender messageSender) {
    this.messageSender = messageSender;
  }

  @Override
  public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
    messageSender.sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
    messageSender.sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
  }

  @Override
  public String toString() {
    return "Hit";
  }
}
