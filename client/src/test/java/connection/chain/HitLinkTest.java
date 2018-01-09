package connection.chain;

import gui.events.YouHitEvent;
import gui.playing.DispatcherAdapter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.HitResponse;
import responses.Response;
import responses.ResponseHeader;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HitLinkTest {

  private DispatcherAdapter dispatcherAdapter;
  private HitLink hitLink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    hitLink = new HitLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenHitResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    Response hitResponse = new HitResponse();

    //Act
    hitLink.analyzeResponse(hitResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(YouHitEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {
    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    hitLink.setNextChain(mockedNextChain);
    Response mockedNoneResponse = mock(Response.class);
    when(mockedNoneResponse.getHeader()).thenReturn(ResponseHeader.NONE);

    //Act
    hitLink.analyzeResponse(mockedNoneResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(mockedNoneResponse, dispatcherAdapter);
  }
}