package engine;

import communication.MessageSender;
import communication.Output;
import communication.PlayerClient;
import communication.PlayerRegistry;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import responses.LossResponse;
import responses.WinResponse;

import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;

public class FinishedGameTest {
  private PlayerRegistry mockedPlayerRegistry;
  private PlayerClient mockedPlayer;
  private PlayerClient mockedLoser;


  @BeforeMethod
  public void setUpt() {
    mockedPlayerRegistry = mock(PlayerRegistry.class);
    mockedPlayer = mock(PlayerClient.class);
    mockedLoser = mock(PlayerClient.class);
    when(mockedPlayerRegistry.getCurrentPlayer()).thenReturn(mockedPlayer);
    when(mockedPlayerRegistry.getWaitingPlayer()).thenReturn(mockedLoser);
    when(mockedPlayerRegistry.getCurrentPlayerName()).thenReturn("Winner");
    when(mockedPlayerRegistry.getWaitingPlayerName()).thenReturn("Loser");
  }


  @Test
  public void shouldSetIsRunningToFalse() throws IOException {
    MessageSender mockedMessageSender = mock(MessageSender.class);
    Output output = mock(Output.class);
    FinishedGame finishedGame = new FinishedGame(mockedPlayerRegistry, mockedMessageSender, output);
    finishedGame.run();

    assertFalse(finishedGame.isGameRunning());
  }

  @Test
  public void shouldSendWinResponseToCurrentPlayer() throws IOException {
    Output output = mock(Output.class);
    MessageSender mockedMessageSender = mock(MessageSender.class);

    FinishedGame finishedGame = new FinishedGame(mockedPlayerRegistry, mockedMessageSender, output);
    finishedGame.run();

    verify(mockedMessageSender).sendResponse(isA(WinResponse.class), eq(mockedPlayer));
  }

  @Test
  public void shouldSendLossResponseToSecondPlayer() throws IOException {
    Output output = mock(Output.class);
    MessageSender mockedMessageSender = mock(MessageSender.class);

    FinishedGame finishedGame = new FinishedGame(mockedPlayerRegistry, mockedMessageSender, output);
    finishedGame.run();

    verify(mockedMessageSender).sendResponse(isA(LossResponse.class), eq(mockedLoser));
  }
}
