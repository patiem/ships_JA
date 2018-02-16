package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.model.ShipModel;
import dto.TranscriptDTO;
import org.testng.annotations.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
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
  @Test
  public void transcriptDTOParseToJson() throws JsonProcessingException {
    //given
    JsonGeneratorAdapter jsonGeneratorAdapter = new JsonGeneratorAdapter();
    TranscriptDTO dto = TranscriptDTO.create("mmfd", new Date());
    ObjectMapper om = new ObjectMapper();
    //when
    String json = jsonGeneratorAdapter.createJson(dto, om);
    //then
    assertThat(json).contains("mmfd");
  }
}