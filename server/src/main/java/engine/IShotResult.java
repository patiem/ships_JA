package engine;

import communication.PlayerRegistry;
import model.Shot;

public interface IShotResult { //TODO: change name to ShotResult
    void notifyClients(PlayerRegistry playerRegistry, Shot shot);

}
