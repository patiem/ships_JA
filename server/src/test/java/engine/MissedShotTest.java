package engine;

import communication.MessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import model.Shot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import responses.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MissedShotTest {
    private MessageSender messageSender;
    private IShotResult shotResult;
    private PlayerRegistry playerRegistry;
    private Shot shot;

    @BeforeMethod
    public void setUp(){
        messageSender = mock(MessageSender.class);
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
}
