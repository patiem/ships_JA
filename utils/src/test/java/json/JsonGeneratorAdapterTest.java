package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.model.ShipModel;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JsonGeneratorAdapterTest {

  @Test
  public void givenMockedObjectAndJacksonMapperWhenCreateJsonThenInvokeJacksonMethod() throws JsonProcessingException {
    JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();
    ShipModel mockedModelToBeParsed = mock(ShipModel.class);
    ObjectMapper mockedMapper = mock(ObjectMapper.class);
    int expectedNumberOfInvocation = 1;

    jsonGeneratorAdapter.createJson(mockedModelToBeParsed, mockedMapper);

    verify(mockedMapper, times(expectedNumberOfInvocation)).writeValueAsString(mockedModelToBeParsed);
  }
}