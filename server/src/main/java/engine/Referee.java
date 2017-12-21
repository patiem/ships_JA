package engine;

import fleet.Fleet;

class Referee {

    GameState isVictory(Fleet fleet) {
        if(fleet.isSunk())
            return GameState.WIN;

        return GameState.ACTIVE;
    }
}
