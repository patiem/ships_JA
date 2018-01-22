package engine;

import communication.SocketMessageSender;
import fleet.Fleet;

import java.util.List;

/**
 * It checks if shots hit the target.
 *
 * @author Emilia Ciastek
 * @version 1.5
 */
class HitChecker {

  private final Fleet fleet;

  HitChecker(final Fleet fleet) {
    this.fleet = fleet;
  }

  ShotResult checkShot(Integer shotPosition) {
    boolean isShipPosition = fleet.getFleetPositions().contains(shotPosition);
    boolean isHitAgain = fleet.getHitFields().contains(shotPosition);

    if (!isHitAgain && isShipPosition) {
      fleet.hit(shotPosition);
      List<Integer> shipPositions = fleet.getShipByPosition(shotPosition).getFields();
      boolean isShipSunk = fleet.getHitFields().containsAll(shipPositions);

      if (isShipSunk) {
        return new ShipSunk(new SocketMessageSender());
      }

      return new ShipHit(new SocketMessageSender());
    }

    return new MissedShot(new SocketMessageSender());
  }
}
