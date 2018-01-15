package connection.chain;

import gui.events.YouLostEvent;
import gui.events.YouMissedEvent;
import gui.playing.DispatcherAdapter;
import model.DummyResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.MissedResponse;
import responses.Response;
import responses.ResponseHeader;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MissedLinkTest {
  private DispatcherAdapter dispatcherAdapter;
  private MissedLink missedLink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    missedLink = new MissedLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenLossResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    MissedResponse missedResponse = new MissedResponse();

    //Act
    missedLink.analyzeResponse(missedResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(YouMissedEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {

    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    missedLink.setNextChain(mockedNextChain);
    DummyResponse dummyResponse = new DummyResponse();

    //Act
    missedLink.analyzeResponse(dummyResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(dummyResponse, dispatcherAdapter);
  }
}
