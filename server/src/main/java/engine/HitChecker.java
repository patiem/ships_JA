package engine;

import fleet.Fleet;

public class HitChecker {

  private Fleet fleet;

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
