package engine;

import communication.PlayerClient;
import communication.PlayerRegistry;
import communication.SocketMessageSender;
import model.Shot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import responses.HitAgainResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HitAgainShotTest {

  private SocketMessageSender messageSender;
  private ShotResult shotResult;
  private PlayerRegistry playerRegistry;
  private PlayerClient currentPlayer;
  private Shot shot;

  @BeforeMethod
  public void setUp() {
    messageSender = mock(SocketMessageSender.class);
    shotResult = new HitAgainShot(messageSender);
    playerRegistry = mock(PlayerRegistry.class);
    currentPlayer = mock(PlayerClient.class);
    when(playerRegistry.getCurrentPlayer()).thenReturn(currentPlayer);
    Integer shotPosition = 5;
    shot = new Shot(shotPosition);
  }

  @Test
  public void shouldInvokeMethodOnMessageSenderWhenNotifyClients() {
    shotResult.notifyClients(playerRegistry, shot);

    verify(messageSender).sendResponse(any(HitAgainResponse.class), eq(currentPlayer));
  }

  @Test
  public void shouldInvokeSwitchPlayersMethodOnPlayerRegistryWhenNotifyClients() {
    shotResult.notifyClients(playerRegistry, shot);

    verify(playerRegistry).switchPlayers();
  }

  @Test
  public void shouldReturnCorrectStringWhenToString() {
    String expected = "HitAgain";

    String actual = shotResult.toString();

    assertThat(actual).isEqualTo(expected);
  }

}