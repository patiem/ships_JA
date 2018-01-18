package connection.chain;

import gui.events.SunkShipEvent;
import gui.playing.DispatcherAdapter;
import model.DummyResponse;
import model.ShipModel;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.Response;
import responses.SunkResponse;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SunkLinkTest {

  private DispatcherAdapter dispatcherAdapter;
  private SunkLink sunkLink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    sunkLink = new SunkLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenHitResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    ShipModel mockedShipModel = mock(ShipModel.class);
    Response sunkResponse = new SunkResponse(mockedShipModel);

    //Act
    sunkLink.analyzeResponse(sunkResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(SunkShipEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {
    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    sunkLink.setNextChain(mockedNextChain);
    DummyResponse dummyResponse = new DummyResponse();

    //Act
    sunkLink.analyzeResponse(dummyResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(dummyResponse, dispatcherAdapter);
  }

}