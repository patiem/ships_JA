package connection.chain;

import gui.events.UpdateWhenMissedEvent;
import gui.playing.DispatcherAdapter;
import model.Shot;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.OpponentMissedResponse;
import responses.Response;
import responses.ResponseHeader;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OpponentMissedLinkTest {
  private DispatcherAdapter dispatcherAdapter;
  private OpponentMissedLink opponentMissedLink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    opponentMissedLink = new OpponentMissedLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenLossResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    Shot mockedShot = mock(Shot.class);
    OpponentMissedResponse opponentMissedResponse = new OpponentMissedResponse(mockedShot);

    //Act
    opponentMissedLink.analyzeResponse(opponentMissedResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(UpdateWhenMissedEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {
    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    opponentMissedLink.setNextChain(mockedNextChain);
    Response mockedNoneResponse = mock(Response.class);
    when(mockedNoneResponse.getHeader()).thenReturn(ResponseHeader.NONE);

    //Act
    opponentMissedLink.analyzeResponse(mockedNoneResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(mockedNoneResponse, dispatcherAdapter);
  }
}
