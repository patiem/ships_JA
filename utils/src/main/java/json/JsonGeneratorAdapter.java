package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGeneratorAdapter {
  public String createJson(Object object, ObjectMapper objectMapper) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }
}