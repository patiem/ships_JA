package engine;

import communication.SocketMessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import model.Shot;
import org.testng.annotations.Test;
import responses.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ShipHitTest {

    @Test
    public void shouldInvokeMethodOnMessageSenderWhenNotifyClients(){
        SocketMessageSender messageSender = mock(SocketMessageSender.class);
        ShotResult shotResult = new ShipHit(messageSender);
        PlayerRegistry playerRegistry = new PlayerRegistry();
        Integer shotPosition = 5;
        Shot shot = new Shot(shotPosition);

        shotResult.notifyClients(playerRegistry, shot);

        int expectedInvocationNumber = 2;
        verify(messageSender, times(expectedInvocationNumber)).sendResponse(any(Response.class), any(PlayerClient.class));
    }

    @Test
    public void shouldReturnCorrectStringWhenToString(){
        ShotResult shotResult = new ShipHit(new SocketMessageSender());
        String expected = "Hit";

        String actual = shotResult.toString();

        assertThat(actual).isEqualTo(expected);
    }
}
