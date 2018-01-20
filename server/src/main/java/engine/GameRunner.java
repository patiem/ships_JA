package engine;

import java.io.IOException;

/**
 * It creates the game events and notifies player accordingly.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */

public class GameRunner {

  private GameState currentState;

  public GameRunner(GameState gameState) {
    this.currentState = gameState;
  }

  public void runGameFixed() throws IOException {
    do {
      currentState = currentState.run();
    }
    while (currentState.isGameRunning());
  }
}
