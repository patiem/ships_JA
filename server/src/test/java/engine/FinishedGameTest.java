package engine;

import communication.MessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import org.testng.annotations.Test;
import responses.LossResponse;
import responses.WinResponse;

import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertFalse;

public class FinishedGameTest {
    @Test
    public void shouldSetIsRunningToFalse() throws IOException {
        MessageSender mockedMessageSender = mock(MessageSender.class);
        FinishedGame finishedGame = new FinishedGame(new PlayerRegistry(), mockedMessageSender);
        finishedGame.runFixed();

        assertFalse(finishedGame.isGameRunning());
    }

    @Test
    public void shouldSendWinResponseToCurrentPlayer() throws IOException {
        MessageSender mockedMessageSender = mock(MessageSender.class);
        PlayerRegistry playerRegistry = mock(PlayerRegistry.class);
        PlayerClient mockedPlayer = mock(PlayerClient.class);
        when(playerRegistry.getCurrentPlayer()).thenReturn(mockedPlayer);

        FinishedGame finishedGame = new FinishedGame(playerRegistry, mockedMessageSender);
        finishedGame.runFixed();

        verify(mockedMessageSender).sendResponse(isA(WinResponse.class), eq(mockedPlayer));
    }

    @Test
    public void shouldSendLossResponseToSecondPlayer() throws IOException {
        MessageSender mockedMessageSender = mock(MessageSender.class);
        PlayerRegistry playerRegistry = mock(PlayerRegistry.class);
        PlayerClient mockedPlayer = mock(PlayerClient.class);
        when(playerRegistry.getWaitingPlayer()).thenReturn(mockedPlayer);

        FinishedGame finishedGame = new FinishedGame(playerRegistry, mockedMessageSender);
        finishedGame.runFixed();

        verify(mockedMessageSender).sendResponse(isA(LossResponse.class), eq(mockedPlayer));
    }
}
