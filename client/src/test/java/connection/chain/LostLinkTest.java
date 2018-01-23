package connection.chain;

import gui.events.YouLostEvent;
import gui.playing.DispatcherAdapter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.LossResponse;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LostLinkTest {

  private DispatcherAdapter dispatcherAdapter;
  private LostLink lostLink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    lostLink = new LostLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenLossResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    LossResponse lossResponse = new LossResponse();

    //Act
    lostLink.analyzeResponse(lossResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(YouLostEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {
    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    lostLink.setNextChain(mockedNextChain);
    DummyResponse dummyResponse = new DummyResponse();

    //Act
    lostLink.analyzeResponse(dummyResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(dummyResponse, dispatcherAdapter);
  }
}
