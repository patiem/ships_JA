package engine;

import communication.PlayerRegistry;
import communication.SocketMessageSender;
import common.model.Shot;
import responses.HitAgainResponse;

public class HitAgainShot implements ShotResult {

  private final SocketMessageSender messageSender;

  HitAgainShot(SocketMessageSender messageSender) {
    this.messageSender = messageSender;
  }

  @Override
  public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
    messageSender.sendResponse(new HitAgainResponse(shot), playerRegistry.getCurrentPlayer());
    playerRegistry.switchPlayers();
  }

  @Override
  public String toString() {
    return "HitAgain";
  }
}
