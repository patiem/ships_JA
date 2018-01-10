package model;

import connection.chain.Chain;
import gui.playing.DispatcherAdapter;
import org.testng.annotations.Test;
import responses.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageProcessorTest {

  @Test
  public void whenReceivedHitLinkObjectThenCallTheAppropriateEvent() {
    Chain mockedFirstLink = mock(Chain.class);
    DispatcherAdapter mockedDispatcherAdapter = mock(DispatcherAdapter.class);
    Response mockedResponse = mock(Response.class);

    MessageProcessor messageProcessor = new MessageProcessor(mockedFirstLink, mockedDispatcherAdapter);
    messageProcessor.processMessage(mockedResponse);
    int expectedInvocations = 1;

    verify(mockedFirstLink, times(expectedInvocations)).analyzeResponse(mockedResponse, mockedDispatcherAdapter);
  }

  //TODO: test whole path (first chain -> exception)

}