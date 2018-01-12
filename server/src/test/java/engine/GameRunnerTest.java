package engine;

import communication.PlayerRegistry;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameRunnerTest {

  @Test
  public void whenTheRunMethodIsCalledLaunchTheGame() throws Exception {

    PlayerRegistry playerRegistry = new PlayerRegistry();
    GameRunnerState activeGameMock = mock(GameRunnerState.class);
    when(activeGameMock.run()).thenReturn(activeGameMock);
    GameRunner gameRunner = new GameRunner(playerRegistry,activeGameMock);
    gameRunner.runGame();

    verify(activeGameMock, times(1)).run();
    
  }

}