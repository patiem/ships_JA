package connection.chain;

import gui.events.UpdateWhenHitEvent;
import gui.playing.DispatcherAdapter;
import model.Shot;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.OpponentHitResponse;
import responses.Response;
import responses.ResponseHeader;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OpponentHitLinkTest {
  private DispatcherAdapter dispatcherAdapter;
  private OpponentHitLink opponentHitLink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    opponentHitLink = new OpponentHitLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenLossResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    Shot mockedShot = mock(Shot.class);
    OpponentHitResponse opponentHitResponse = new OpponentHitResponse(mockedShot);

    //Act
    opponentHitLink.analyzeResponse(opponentHitResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(UpdateWhenHitEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {
    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    opponentHitLink.setNextChain(mockedNextChain);
    Response mockedNoneResponse = mock(Response.class);
    when(mockedNoneResponse.getHeader()).thenReturn(ResponseHeader.NONE);

    //Act
    opponentHitLink.analyzeResponse(mockedNoneResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(mockedNoneResponse, dispatcherAdapter);
  }
}
