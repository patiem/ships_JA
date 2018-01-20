package engine;

import communication.SocketMessageSender;
import communication.PlayerRegistry;
import model.Shot;
import responses.MissedResponse;
import responses.OpponentMissedResponse;

public class MissedShot implements IShotResult {
    private final SocketMessageSender messageSender;

    MissedShot(SocketMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
        messageSender.sendResponse(new MissedResponse(), playerRegistry.getCurrentPlayer());
        messageSender.sendResponse(new OpponentMissedResponse(shot), playerRegistry.getWaitingPlayer());
        playerRegistry.switchPlayers();
    }

    @Override
    public String toString() {
        return "Missed";
    }
}
