package engine;

import common.model.ShipModel;
import common.model.Shot;
import communication.PlayerRegistry;
import communication.SocketMessageSender;
import fleet.Fleet;
import responses.OpponentHitResponse;
import responses.SunkResponse;

public class ShipSunk implements ShotResult {
  private final SocketMessageSender messageSender;

  ShipSunk(SocketMessageSender messageSender) {
    this.messageSender = messageSender;
  }

  @Override
  public void notifyClients(PlayerRegistry playerRegistry, Shot shot) {
    Fleet fleetUnderFire = playerRegistry.getFleetUnderFire();
    ShipModel sunkShip = fleetUnderFire.getShipByPosition(shot.asInteger());
    messageSender.sendResponse(new SunkResponse(sunkShip), playerRegistry.getCurrentPlayer());
    messageSender.sendResponse(new OpponentHitResponse(shot), playerRegistry.getWaitingPlayer());
  }

  @Override
  public String toString() {
    return "Sunk";
  }
}
