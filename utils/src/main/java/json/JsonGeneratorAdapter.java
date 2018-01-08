package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

/**
 * It creates Json messages.
 * @author Emilia Ciastek/Bartosz Pieczara
 * @version 1.5
 */
public class JsonGeneratorAdapter {
  public String createJson(Object object, ObjectMapper mapper) throws JsonProcessingException {
    mapper.registerModule(new Jdk8Module());
    return mapper.writeValueAsString(object);
  }
}