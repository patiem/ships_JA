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

public class ShipHitTest {

    @Test
    public void shouldInvokeMethodOnMessageSenderWhenNotifyClients(){
        MessageSender messageSender = mock(MessageSender.class);
        IShotResult shotResult = new ShipHit(messageSender);
        PlayerRegistry playerRegistry = new PlayerRegistry();
        Integer shotPosition = 5;
        Shot shot = new Shot(shotPosition);

        shotResult.notifyClients(playerRegistry, shot);

        int expectedInvocationNumber = 2;
        verify(messageSender, times(expectedInvocationNumber)).sendResponse(any(Response.class), any(PlayerClient.class));
    }


//    @Test
//    public void shouldReturnCorrectStringWhenToString(){
//        String expected = "Hit";
//
//        String actual =
//
//    }
}
