package engine;

import communication.SocketMessageSender;
import communication.PlayerRegistry;
import fleet.Fleet;
import model.ShipModel;
import model.Shot;
import responses.HitResponse;
import responses.OpponentHitResponse;
import responses.SunkResponse;

public class ShipSunk implements IShotResult {
    private final SocketMessageSender messageSender;

    ShipSunk(SocketMessageSender messageSender) {
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

    @Override
    public String toString() {
        return "Sunk";
    }
}
