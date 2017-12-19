package model;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

    private final List<Mast> masts;
    private final Sea sea;
    private final List<Ship> fleet;
    private Ship shipThatIsBuild;

    public Fleet(Sea sea) {
        this.sea = sea;
        masts = new ArrayList<>();
        fleet = new ArrayList<>();
    }

    private void addFirstMastToFleet(Mast mast) {
        masts.add(mast);
    }

    public void startToBuildOneShip(Mast mast, int shipLength) {
        addFirstMastToFleet(mast);
        Ship ship = new Ship(mast, shipLength);
        shipThatIsBuild = ship;
        fleet.add(ship);
        createNewMast(mast);
    }

    private void createNewMast(Mast mast) {
        if (shipThatIsBuild.isShipDone()) {
            sea.clearSea();
            sea.makeBoundaries(shipThatIsBuild);
            return;
        }
        PossiblePosition possible = new PossiblePosition(mast, sea);
        possible.showPossibleMastPosition();
    }

    public void addNextMastToShip(Mast mast) {
        shipThatIsBuild.addMast(mast);
        createNewMast(mast);
    }

    public List<Ship> getShips() {
        return fleet;
    }
}
