package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JsonParserAdapterTest {

  @Test
  public void givenMessageAndMockedJacksonMapperWhenParseThenInvokeJacksonMethod() throws IOException {
    int expectedNumberOfInvocation = 1;
    JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();
    ObjectMapper mockedMapper = mock(ObjectMapper.class);
    String jsonMessage = "{\"name\":\"name value\",\"age\":156,\"positions\":[1,2,3,4,5,6,7,8,9,10]}";
    Class<DummyTestObject> classToParse = DummyTestObject.class;

    jsonParserAdapter.parse(jsonMessage, classToParse, mockedMapper);

    verify(mockedMapper, times(expectedNumberOfInvocation)).readValue(jsonMessage, classToParse);
  }
}