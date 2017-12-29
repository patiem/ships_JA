package json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParserAdapter {
  public <T> T parse(String jsonMessage, Class<T> objectClass, ObjectMapper mapper) throws IOException {
    return mapper.readValue(jsonMessage, objectClass);
  }
}
