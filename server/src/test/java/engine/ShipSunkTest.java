package engine;

import communication.SocketMessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import fleet.HardcodedFleet;
import common.model.Shot;
import org.testng.annotations.Test;
import responses.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ShipSunkTest {

    @Test
    public void shouldInvokeMethodOnMessageSenderWhenNotifyClients() {
        SocketMessageSender messageSender = mock(SocketMessageSender.class);
        ShotResult shotResult = new ShipSunk(messageSender);
        PlayerRegistry playerRegistry = mock(PlayerRegistry.class);
        when(playerRegistry.getFleetUnderFire()).thenReturn(new HardcodedFleet());

        Integer shotPosition = 3;
        Shot shot = new Shot(shotPosition);

        shotResult.notifyClients(playerRegistry, shot);

        int expectedInvocationNumber = 3;
        verify(messageSender, times(expectedInvocationNumber)).sendResponse(any(Response.class), any(PlayerClient.class));
    }

    @Test
    public void shouldReturnCorrectStringWhenToString(){
        ShotResult shotResult = new ShipSunk(new SocketMessageSender());
        String expected = "Sunk";

        String actual = shotResult.toString();

        assertThat(actual).isEqualTo(expected);
    }
}