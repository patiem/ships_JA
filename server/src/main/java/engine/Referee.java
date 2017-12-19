package engine;

import fleet.Fleet;

class Referee {
    boolean isVictory(Fleet fleet) {
        return fleet.isSunk();
    }
}
