package engine;

import fleet.Fleet;

public class Round {

    ShotResult makeShot(Fleet fleetUnderFire, Shot shot) {
        Integer shotPosition = shot.asInteger();
        HitChecker hitChecker = new HitChecker(fleetUnderFire);
        ShotResult shotState = hitChecker.checkShot(shotPosition);

        return shotState;
    }
/*
    private void showInfoAboutCurrentShot(Integer hit, ShotResult shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerTracker.currentPlayerName(), hit, shotState));
    }
    */
}
