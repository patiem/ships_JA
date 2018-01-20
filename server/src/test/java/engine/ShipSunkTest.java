package engine;

import communication.MessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import fleet.HardcodedFleet;
import model.Shot;
import org.testng.annotations.Test;
import responses.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ShipSunkTest {

    @Test
    public void shouldInvokeMethodOnMessageSenderWhenNotifyClients() {
        MessageSender messageSender = mock(MessageSender.class);
        IShotResult shotResult = new ShipSunk(messageSender);
        PlayerRegistry playerRegistry = mock(PlayerRegistry.class);
        when(playerRegistry.getFleetUnderFire()).thenReturn(new HardcodedFleet());

        Integer shotPosition = 3;
        Shot shot = new Shot(shotPosition);

        shotResult.notifyClients(playerRegistry, shot);

        int expectedInvocationNumber = 3;
        verify(messageSender, times(expectedInvocationNumber)).sendResponse(any(Response.class), any(PlayerClient.class));
    }
}