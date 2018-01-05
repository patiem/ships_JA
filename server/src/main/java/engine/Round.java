package engine;

import fleet.Fleet;

/**
 * It returns the shot result.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class Round {

  ShotResult fireShot(Fleet fleetUnderFire, Shot shot) {
    Integer shotPosition = shot.asInteger();
    HitChecker hitChecker = new HitChecker(fleetUnderFire);

    return hitChecker.checkShot(shotPosition);
  }
}
