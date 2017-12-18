package model;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

    private final List<Mast> masts;
    private final Sea sea;
    private final List<Ship> fleet;
    private Ship lastShip;

    public Fleet(Sea sea) {
        this.sea = sea;
        masts = new ArrayList<>();
        fleet = new ArrayList<>();
    }

    private void addFirstMastToShip(Mast mast) {
        masts.add(mast);
    }

    public void startToBuilOneShip(Mast mast) {
        addFirstMastToShip(mast);
        Ship ship = new Ship(mast);
        lastShip = ship;
        fleet.add(ship);
        if(lastShip.isShipDone()) {
            sea.clearSea();
            sea.makeBoundaries(lastShip);
        } else {
            createNewMast(mast);
        }
    }

    private void createNewMast(Mast mast) {
        PossiblePosition possible = new PossiblePosition(mast, sea);
        possible.showPossibleMastPosition();
    }

    public void addNextMastToShip(Mast mast) {
        lastShip.addMast(mast);
        createNewMast(mast);
    }
}
