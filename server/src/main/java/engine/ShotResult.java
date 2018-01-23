package engine;

import communication.PlayerRegistry;
import common.model.Shot;

public interface ShotResult {
  void notifyClients(PlayerRegistry playerRegistry, Shot shot);
}
