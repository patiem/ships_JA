package engine;

import communication.SocketMessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import common.model.Shot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import responses.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MissedShotTest {
    private SocketMessageSender messageSender;
    private ShotResult shotResult;
    private PlayerRegistry playerRegistry;
    private Shot shot;

    @BeforeMethod
    public void setUp(){
        messageSender = mock(SocketMessageSender.class);
        shotResult = new MissedShot(messageSender);
        playerRegistry = mock(PlayerRegistry.class);
        Integer shotPosition = 5;
        shot = new Shot(shotPosition);
    }

    @Test
    public void shouldInvokeMethodOnMessageSenderWhenNotifyClients(){
        shotResult.notifyClients(playerRegistry, shot);

        int expectedInvocationNumber = 2;
        verify(messageSender, times(expectedInvocationNumber)).sendResponse(any(Response.class), any(PlayerClient.class));
    }

    @Test
    public void shouldInvokeSwitchPlayersMethodOnPlayerRegistryWhenNotifyClients(){
        shotResult.notifyClients(playerRegistry, shot);

        verify(playerRegistry).switchPlayers();
    }

    @Test
    public void shouldReturnCorrectStringWhenToString(){
        String expected = "Missed";

        String actual = shotResult.toString();

        assertThat(actual).isEqualTo(expected);
    }
}
