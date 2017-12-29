package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGeneratorAdapter {
  public String createJson(Object object, ObjectMapper objectMapper) throws JsonProcessingException {
    String jsonString = objectMapper.writeValueAsString(object);

    return jsonString;
  }
}