package connection.chain;

import gui.events.YouWinEvent;
import gui.playing.DispatcherAdapter;
import model.DummyResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import responses.Response;
import responses.ResponseHeader;
import responses.WinResponse;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WinLinkTest {
  private DispatcherAdapter dispatcherAdapter;
  private WinLink winLink;
  private int expectedInvocations;

  @BeforeTest
  public void setUp() {
    dispatcherAdapter = mock(DispatcherAdapter.class);
    winLink = new WinLink();
    expectedInvocations = 1;
  }

  @Test
  public void givenLossResponseWhenAnalyzeThenInvokeMethodOnDispatcher() {
    //Arrange
    WinResponse winResponse = new WinResponse();

    //Act
    winLink.analyzeResponse(winResponse, dispatcherAdapter);

    //Assert
    verify(dispatcherAdapter, times(expectedInvocations)).fireEvent(isA(YouWinEvent.class));
  }

  @Test
  public void givenNoneResponseWhenAnalyzeThenPassedItOnToTheNextLink() {
    //Arrange
    Chain mockedNextChain = mock(Chain.class);
    winLink.setNextChain(mockedNextChain);
    DummyResponse dummyResponse = new DummyResponse();

    //Act
    winLink.analyzeResponse(dummyResponse, dispatcherAdapter);

    //Assert
    verify(mockedNextChain, times(expectedInvocations))
        .analyzeResponse(dummyResponse, dispatcherAdapter);
  }
}
