package engine;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameRunnerTest {

  @Test
  public void shouldInvokeRunMethodOnProvidedState() throws IOException {
    GameState mockedGameState = mock(GameState.class);
    GameRunner gameRunner = new GameRunner(mockedGameState);

    when(mockedGameState.isGameRunning()).thenReturn(false);
    when(mockedGameState.run()).thenReturn(mockedGameState);

    gameRunner.runGame();

    verify(mockedGameState, times(1)).run();
  }
}
