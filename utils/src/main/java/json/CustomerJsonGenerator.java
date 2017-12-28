package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerJsonGenerator {
  public String createJson(Object object) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(object);

    return jsonString;
  }
}