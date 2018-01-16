package engine;

import fleet.Fleet;

/**
 * It checks if shots hit the target.
 *
 * @author Emilia Ciastek
 * @version 1.5
 */
public class HitChecker {

  private final Fleet fleet;

  public HitChecker(final Fleet fleet) {
    this.fleet = fleet;
  }

  public ShotResult checkShot(final int position) {
    boolean isShipPosition = fleet.getFleetPositions().contains(position);
    boolean isHitAgain = fleet.getHitFields().contains(position);

    if (!isHitAgain && isShipPosition) {
      fleet.hit(position);
      boolean isShipSunk = fleet.getHitFields().containsAll(fleet.getShipByPosition(position).getFields());
      if(isShipSunk){

        return ShotResult.SUNK;
      }
      return ShotResult.HIT;
    }

    return ShotResult.MISSED;
  }
}
