package engine;

import communication.MessageSender;
import communication.PlayerRegistry;
import fleet.Fleet;
import model.ShipModel;
import model.Shot;
import responses.HitResponse;
import responses.OpponentHitResponse;
import responses.SunkResponse;

public class ShipSunk implements IShotResult {
    private final MessageSender messageSender;

    public ShipSunk(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
        Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
        ShipModel sunkShip = fleetUnderFire.getShipByPosition(shot.asInteger());
        messageSender.sendResponse(new HitResponse(), playerRegistry.getCurrentPlayer());
        messageSender.sendResponse(new SunkResponse(sunkShip), playerRegistry.getCurrentPlayer());
        messageSender.sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
    }
}
