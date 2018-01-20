package engine;

import communication.PlayerRegistry;
import model.Shot;

public class MissedShot implements IShotResult {


    @Override
    public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {

    }
}
