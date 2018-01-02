package engine;

import fleet.Fleet;

/**
 * It returns the shot result.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class Round {

  ShotResult makeShot(Fleet fleetUnderFire, Shot shot) {
    Integer shotPosition = shot.asInteger();
    HitChecker hitChecker = new HitChecker(fleetUnderFire);
    ShotResult shotState = hitChecker.checkShot(shotPosition);

    return shotState;
  }
}
