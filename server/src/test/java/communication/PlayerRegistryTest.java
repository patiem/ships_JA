package communication;

import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class PlayerRegistryTest {

  @Test
  public void givenTwoMockedPlayers_WhenSwitchPlayers_ThenSecondPlayerIsCurrentPlayer(){
    //Given
    String firstPlayerName = "first player";
    String secondPlayerName = "second player";
    PlayerClient mockedFirstPlayer = mock(PlayerClient.class);
    when(mockedFirstPlayer.getName()).thenReturn(firstPlayerName);
    PlayerClient mockedSecondPlayer = mock(PlayerClient.class);
    when(mockedSecondPlayer.getName()).thenReturn(secondPlayerName);
    TranscriptPanel panel = mock(TranscriptPanel.class);

    PlayerRegistry playerRegistry = new PlayerRegistry();
    playerRegistry.registerPlayer(mockedFirstPlayer, panel);
    playerRegistry.registerPlayer(mockedSecondPlayer, panel);

    //When
    playerRegistry.switchPlayers();

    //Then
    assertEquals(playerRegistry.currentPlayerName(), secondPlayerName);
  }

}