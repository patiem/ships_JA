package connection.chain;

import gui.events.HitAgainEvent;
import gui.playing.DispatcherAdapter;
import model.DummyResponse;
import common.model.Shot;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.HitAgainResponse;
import responses.Response;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HitAgainLinkTest {
  private DispatcherAdapter dispatcherAdapter;
  private HitAgainLink hitAgainlink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    hitAgainlink = new HitAgainLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenHitResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    Shot mockedShot = mock(Shot.class);
    Response hitAgainResponse = new HitAgainResponse(mockedShot);

    //Act
    hitAgainlink.analyzeResponse(hitAgainResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(HitAgainEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {
    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    hitAgainlink.setNextChain(mockedNextChain);
    DummyResponse dummyResponse = new DummyResponse();

    //Act
    hitAgainlink.analyzeResponse(dummyResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(dummyResponse, dispatcherAdapter);
  }

}