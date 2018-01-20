package engine;

import communication.PlayerRegistry;
import model.Shot;

public interface ShotResult {
    void notifyClients(PlayerRegistry playerRegistry, Shot shot);
}
