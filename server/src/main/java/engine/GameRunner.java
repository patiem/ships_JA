package engine;

import communication.PlayerRegistry;
import responses.PlayResponse;


/**
 * It creates the game events and notifies player accordingly.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */

public class GameRunner {

  private final PlayerRegistry playerRegistry;
  private GameRunnerState activeState;
  private GameRunnerState currentState;

  public GameRunner( final PlayerRegistry playerRegistry) {
    this.playerRegistry = playerRegistry;
    this.activeState = new ActiveGame(playerRegistry);
    this.currentState = activeState;
  }

  public void runGame() {
    currentState.sendResponse(new PlayResponse(), playerRegistry.getCurrentPlayer());
    currentState = currentState.run();
    currentState.sendResponse();
  }
}
