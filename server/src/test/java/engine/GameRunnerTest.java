package engine;

import communication.PlayerClient;
import communication.PlayerRegistry;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import responses.PlayResponse;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameRunnerTest {

  private GameRunnerState activeGameMock;
  private FinishedGame finishedGameMock;
  private PlayerRegistry playerRegistry;
  private GameRunner gameRunner;

  @BeforeMethod
  public void setUp() {
    activeGameMock = mock(GameRunnerState.class);
    playerRegistry = mock(PlayerRegistry.class);
    gameRunner = new GameRunner(playerRegistry, activeGameMock);
    finishedGameMock = mock(FinishedGame.class);
  }

  @Test
  public void whenTheRunMethodIsCalledLaunchTheGame() throws Exception {
    // Arrange
    when(activeGameMock.run()).thenReturn(finishedGameMock);
    // Act
    gameRunner.runGame();
    // Assert
    verify(activeGameMock, times(1)).run();
  }

  @Test
  public void whenTheRunMethodIsCalledThenInvokeTheSendResponseMethod() throws Exception {
    // Arrange
    when(activeGameMock.run()).thenReturn(finishedGameMock);
    // Act
    gameRunner.runGame();
    // Assert
    verify(finishedGameMock, times(1)).sendFinalResponse();
  }

  @Test
  public void whenTheRunMethodIsCalledThenInvokeTheSendResponseMethodWithArguments() throws Exception {
    // Arrange
    PlayerClient playerClient = mock(PlayerClient.class);
    when(playerRegistry.getCurrentPlayer()).thenReturn(playerClient);
    when(activeGameMock.run()).thenReturn(finishedGameMock);
    // Act
    gameRunner.runGame();
    // Assert
    verify(activeGameMock, times(1)).sendResponse(any(PlayResponse.class),any(PlayerClient.class));
  }
}