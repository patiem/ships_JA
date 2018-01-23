package connection.chain;

import gui.playing.DispatcherAdapter;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class EndLinkTest {

  @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown response with header: NONE")
  public void whenAnalyzeResponseThenThrowException() {
    //Arrange
    DispatcherAdapter mockedDispatcherAdapter = mock(DispatcherAdapter.class);
    DummyResponse dummyResponse = new DummyResponse();
    EndLink endLink = new EndLink();
    //Act
    endLink.analyzeResponse(dummyResponse, mockedDispatcherAdapter);
    //Assert
    //Throw Exception
  }

  @Test(expectedExceptions = UnsupportedOperationException.class)
  public void whenSetNextChainThenThrowException() {
    //Arrange
    EndLink endLink = new EndLink();
    Chain mockedNextChain = mock(Chain.class);
    //Act
    endLink.setNextChain(mockedNextChain);
    //Assert
    // Throw exception
  }
}
