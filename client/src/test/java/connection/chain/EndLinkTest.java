package connection.chain;

import gui.playing.DispatcherAdapter;
import org.testng.annotations.Test;
import responses.Response;
import responses.ResponseHeader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EndLinkTest {

  @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown response with header: NONE")
  public void whenAnalyzeResponseThenThrowException() {
    DispatcherAdapter mockedDispatcherAdapter = mock(DispatcherAdapter.class);
    Response mockedResponse = mock(Response.class);
    when(mockedResponse.getHeader()).thenReturn(ResponseHeader.NONE);
    EndLink endLink = new EndLink();

    endLink.analyzeResponse(mockedResponse, mockedDispatcherAdapter);
  }

  @Test(expectedExceptions = UnsupportedOperationException.class)
  public void whenSetNextChainThenThrowException() {
    EndLink endLink = new EndLink();
    Chain mockedNextChain = mock(Chain.class);

    endLink.setNextChain(mockedNextChain);
  }
}