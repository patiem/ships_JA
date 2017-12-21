package engine;

import fleet.Fleet;

public class Round {

    ShotResult makeShot(Fleet fleetUnderFire, Shot shot) {
        Integer shotPosition = shot.asInteger();
        HitChecker hitChecker = new HitChecker(fleetUnderFire);
        ShotResult shotState = hitChecker.checkShot(shotPosition);

        return shotState;
    }
}
