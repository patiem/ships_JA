package json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * It parses Json messages.
 * @author Emilia Ciastek/Bartosz Pieczara
 * @version 1.5
 */
public class JsonParserAdapter {
  public <T> T parse(String jsonMessage, Class<T> objectClass, ObjectMapper mapper) throws IOException {
    return mapper.readValue(jsonMessage, objectClass);
  }
}
