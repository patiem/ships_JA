package engine;

import fleet.Fleet;

/**
 * It checks if one of the players has won.
 * @author Emilia Ciastek
 * @version 1.5
 */
class Referee {

  GameState isVictory(Fleet fleet) {
    if (fleet.isSunk()) {
      return GameState.WIN;
    }

    return GameState.ACTIVE;
  }
}
