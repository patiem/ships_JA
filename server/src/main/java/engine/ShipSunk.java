package engine;

import communication.PlayerClient;
import communication.PlayerRegistry;
import fleet.Fleet;
import model.ShipModel;
import model.Shot;
import responses.HitResponse;
import responses.OpponentHitResponse;
import responses.SunkResponse;

public class ShipSunk implements ShotResult {

    @Override
    public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
        PlayerClient currentPlayer = playerRegistry.getCurrentPlayer();
        PlayerClient waitingPlayer = playerRegistry.getWaitingPlayer();
        Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
        ShipModel sunkShip = fleetUnderFire.getShipByPosition(shot.asInteger());
        currentPlayer.sendResponse(new HitResponse());
        currentPlayer.sendResponse(new SunkResponse(sunkShip));
        waitingPlayer.sendResponse(new OpponentHitResponse(shot));
    }

    @Override
    public String toString() {
        return "Sunk";
    }
}
