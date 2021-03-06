package engine;

import fleet.Fleet;
import common.model.Shot;

/**
 * It returns the shot result.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
class Round {

  ShotResult fireShot(Fleet fleetUnderFire, Shot shot) {
    Integer shotPosition = shot.asInteger();
    HitChecker hitChecker = new HitChecker(fleetUnderFire);

    return hitChecker.checkShot(shotPosition);
  }
}
