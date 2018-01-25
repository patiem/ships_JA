package engine;

import common.model.Shot;
import communication.PlayerRegistry;

public class MissedShot implements ShotResult {

  @Override
  public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
    playerRegistry.switchPlayers();
  }

  @Override
  public String toString() {
    return "Missed";
  }
}
