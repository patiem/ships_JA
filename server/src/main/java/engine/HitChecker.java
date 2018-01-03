package engine;

import fleet.Fleet;

/**
 * It checks if shots hit the target.
 * @author Emilia Ciastek
 * @version 1.5
 */
public class HitChecker {

  private final Fleet fleet;

  public HitChecker(final Fleet fleet) {
    this.fleet = fleet;
  }

  public ShotResult checkShot(final int position) {
    if (fleet.getHitFields().contains(position)) {
      return ShotResult.HIT_AGAIN;
    } else if (fleet.getFleetPositions().contains(position)) {
      fleet.hit(position);
      return ShotResult.HIT;
    } else {
      return ShotResult.MISSED;
    }
  }

}
