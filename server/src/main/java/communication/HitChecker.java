package communication;

import fleet.Fleet;

public class HitChecker {

    private Fleet fleet;

    public HitChecker(Fleet fleet) {
        this.fleet = fleet;
    }

    public ShotState checkShot(int position) {
        if (fleet.getHitFields().contains(position)) {
            return ShotState.HIT_AGAIN;
        } else if (fleet.getFleetPositions().contains(position)) {
            fleet.hit(position);
            return ShotState.HIT;
        } else {
            return ShotState.MISSED;
        }
    }

}
