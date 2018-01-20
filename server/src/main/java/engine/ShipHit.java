package engine;

import communication.PlayerClient;
import communication.SocketMessageSender;
import communication.PlayerRegistry;
import model.Shot;
import responses.HitResponse;
import responses.OpponentHitResponse;

public class ShipHit implements ShotResult {

    @Override
    public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
        PlayerClient currentPlayer = playerRegistry.getCurrentPlayer();
        PlayerClient waitingPlayer = playerRegistry.getWaitingPlayer();
        currentPlayer.sendResponse(new HitResponse());
        waitingPlayer.sendResponse(new OpponentHitResponse(shot));
    }

    @Override
    public String toString() {
        return "Hit";
    }
}
