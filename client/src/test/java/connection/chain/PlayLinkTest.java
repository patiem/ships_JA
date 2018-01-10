package connection.chain;

import gui.events.YourTurnEvent;
import gui.playing.DispatcherAdapter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.PlayResponse;
import responses.Response;
import responses.ResponseHeader;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayLinkTest {
  private DispatcherAdapter dispatcherAdapter;
  private PlayLink playLink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    playLink = new PlayLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenLossResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    PlayResponse playResponse = new PlayResponse();

    //Act
    playLink.analyzeResponse(playResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(YourTurnEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {
    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    playLink.setNextChain(mockedNextChain);
    Response mockedNoneResponse = mock(Response.class);
    when(mockedNoneResponse.getHeader()).thenReturn(ResponseHeader.NONE);

    //Act
    playLink.analyzeResponse(mockedNoneResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(mockedNoneResponse, dispatcherAdapter);
  }
}
