package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;

/**
 * It parses Json messages.
 * @author Emilia Ciastek/Bartosz Pieczara
 * @version 1.5
 */
public class JsonParserAdapter {
  public <T> T parse(String jsonMessage, Class<T> objectClass,
                     ObjectMapper mapper) throws IOException {
    mapper.registerModule(new Jdk8Module());
    return mapper.readValue(jsonMessage, objectClass);
  }
}
