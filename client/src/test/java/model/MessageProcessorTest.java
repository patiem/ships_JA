package model;

import connection.MessageProcessor;
import connection.chain.Chain;
import connection.chain.ChainConfigFactory;
import connection.chain.DummyResponse;
import gui.playing.DispatcherAdapter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import responses.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageProcessorTest {

  private DispatcherAdapter mockedDispatcherAdapter;
  private Response response;

  @BeforeMethod
  public void setUpTests(){
    // Arrange
    mockedDispatcherAdapter = mock(DispatcherAdapter.class);
    response = new DummyResponse();
  }

  @Test
  public void whenReceivedHitLinkObjectThenCallTheAppropriateEvent() {
    // Arrange
    int expectedInvocations = 1;
    Chain mockedFirstLink = mock(Chain.class);
    MessageProcessor messageProcessor = new MessageProcessor(mockedFirstLink, mockedDispatcherAdapter);
    // Act
    messageProcessor.processMessage(response);

    // Assert
    verify(mockedFirstLink, times(expectedInvocations)).analyzeResponse(response, mockedDispatcherAdapter);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void whenReceivedAMessageThatDoesNotMatchAnyOfTheLinksThrowIllegalArgumentException() {
    // Arrange
    Chain hitLink = ChainConfigFactory.configureChainOfResponsibilities();
    MessageProcessor messageProcessor = new MessageProcessor(hitLink, mockedDispatcherAdapter);
    // Act
    messageProcessor.processMessage(response);
    // Assert
    // Throw IllegalArgumentException
  }
}