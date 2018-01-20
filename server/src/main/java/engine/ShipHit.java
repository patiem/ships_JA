package engine;

import communication.MessageSender;
import communication.PlayerRegistry;
import model.Shot;
import responses.HitResponse;
import responses.OpponentHitResponse;

public class ShipHit implements IShotResult {
    private final MessageSender messageSender;

    ShipHit(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
        messageSender.sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
        messageSender.sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
    }

    @Override
    public String toString() {
        return "Hit";
    }
}
