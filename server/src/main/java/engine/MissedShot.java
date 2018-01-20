package engine;

import communication.MessageSender;
import communication.PlayerRegistry;
import model.Shot;
import responses.MissedResponse;
import responses.OpponentMissedResponse;

public class MissedShot implements IShotResult {
    private final MessageSender messageSender;

    public MissedShot(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
        messageSender.sendResponse(new MissedResponse(), playerRegistry.getCurrentPlayer());
        messageSender.sendResponse(new OpponentMissedResponse(shot), playerRegistry.getWaitingPlayer());
        playerRegistry.switchPlayers();
    }
}
