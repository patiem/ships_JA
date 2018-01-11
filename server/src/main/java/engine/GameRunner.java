package engine;

import communication.PlayerRegistry;
import responses.PlayResponse;

import java.io.IOException;

/**
 * It creates the game events and notifies player accordingly.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */

public class GameRunner {

  private final PlayerRegistry playerRegistry;
  private GameRunnerState currentState;

  public GameRunner(final PlayerRegistry playerRegistry) {
    this.playerRegistry = playerRegistry;
    this.currentState = new ActiveGame(playerRegistry);
  }

  public void runGame() throws IOException {
    currentState.sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());
    currentState = currentState.run();
    currentState.sendResponse();
  }
}
