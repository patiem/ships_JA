package engine;

import communication.PlayerRegistry;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameRunnerTest {

  private GameState activeGameMock;
  private FinishedGame finishedGameMock;
  private PlayerRegistry playerRegistry;
  private GameRunner gameRunner;

  @BeforeMethod
  public void setUp() {
    activeGameMock = mock(GameState.class);
    playerRegistry = mock(PlayerRegistry.class);
    gameRunner = new GameRunner(activeGameMock);
    finishedGameMock = mock(FinishedGame.class);
  }

//  @Test
//  public void whenTheRunMethodIsCalledLaunchTheGame() throws Exception {
//    // Arrange
//    when(activeGameMock.run()).thenReturn(finishedGameMock);
//    // Act
//    gameRunner.runGame();
//    // Assert
//    verify(activeGameMock, times(1)).run();
//  }
//
//  @Test
//  public void whenTheRunMethodIsCalledThenInvokeTheSendResponseMethod() throws Exception {
//    // Arrange
//    when(activeGameMock.run()).thenReturn(finishedGameMock);
//    // Act
//    gameRunner.runGame();
//    // Assert
//    verify(finishedGameMock, times(1)).sendFinalResponse();
//  }
//
//  @Test
//  public void whenTheRunMethodIsCalledThenInvokeTheSendResponseMethodWithArguments() throws Exception {
//    // Arrange
//    PlayerClient playerClient = mock(PlayerClient.class);
//    when(playerRegistry.getCurrentPlayer()).thenReturn(playerClient);
//    when(activeGameMock.run()).thenReturn(finishedGameMock);
//    // Act
//    gameRunner.runGame();
//    // Assert
//    verify(activeGameMock, times(1)).sendResponse(any(PlayResponse.class),any(PlayerClient.class));
//  }

  @Test
  public void shouldInvokeRunMethodOnProvidedState() throws IOException {
    when(activeGameMock.isGameRunning()).thenReturn(false);
    when(activeGameMock.run()).thenReturn(activeGameMock);

    gameRunner.runGameFixed();

    verify(activeGameMock, times(1)).run();
  }
}
