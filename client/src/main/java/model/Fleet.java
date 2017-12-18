package model;

import java.util.ArrayList;
import java.util.List;

public class Fleet {


    private List<Mast> mastList;
    private Sea sea;
    private List<Ship> flota;
    private Ship lastShip;


    public Fleet(Sea sea) {
        this.sea = sea;
        mastList = new ArrayList<>();
        flota = new ArrayList<>();
    }

    void add(Mast mast) {
        System.out.println(mast);
        mastList.add(mast);
    }

    public void buildShip(Mast mast) {
        add(mast);
        Ship ship = new Ship(mast);
        lastShip = ship;
        flota.add(ship);
        createNewMast(mast);
    }

    private void createNewMast(Mast mast) { //TODO: this methods has two responsibilities
        if(lastShip.isShipDone()) {
            sea.clearSea();
            sea.makeBoundaries(lastShip);
            return;
        }
        PossiblePosition possible = new PossiblePosition(mast, sea);
        possible.showPossibleMastPosition();
    }

    public void glueMast(Mast mast) {
        lastShip.addMast(mast);
        System.out.println(flota);
        createNewMast(mast);
    }
}
