package engine;

import communication.PlayerClient;
import communication.SocketMessageSender;
import communication.PlayerRegistry;
import model.Shot;
import responses.MissedResponse;
import responses.OpponentMissedResponse;

public class MissedShot implements ShotResult {
    @Override
    public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
       PlayerClient currentPlayer = playerRegistry.getCurrentPlayer();
       PlayerClient waitingPlayer = playerRegistry.getWaitingPlayer();
        currentPlayer.sendResponse(new MissedResponse());
        waitingPlayer.sendResponse(new OpponentMissedResponse(shot));
        playerRegistry.switchPlayers();
    }

    @Override
    public String toString() {
        return "Missed";
    }
}
