package engine;

import java.io.IOException;

/**
 * It creates the game events and notifies player accordingly.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */

public class GameRunner {

  private GameRunnerState currentState;

  public GameRunner(GameRunnerState gameRunnerState) {
    this.currentState = gameRunnerState;
  }

  public void runGameFixed() throws IOException {
    do {
      currentState = currentState.runFixed();
    }
    while (currentState.isGameRunning());
  }
}
