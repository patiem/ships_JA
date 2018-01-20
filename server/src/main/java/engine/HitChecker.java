package engine;

import communication.MessageSender;
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

    IShotResult checkShotFixed(Integer shotPosition) {
        boolean isShipPosition = fleet.getFleetPositions().contains(shotPosition);
        boolean isHitAgain = fleet.getHitFields().contains(shotPosition);

        if (!isHitAgain && isShipPosition) {
            fleet.hit(shotPosition);
            List<Integer> shipPositions = fleet.getShipByPosition(shotPosition).getFields();
            boolean isShipSunk = fleet.getHitFields().containsAll(shipPositions);

            if (isShipSunk) {
                return new ShipSunk(new MessageSender());
            }

            return new ShipHit(new MessageSender());
        }

        return new MissedShot(new MessageSender());
    }
}
