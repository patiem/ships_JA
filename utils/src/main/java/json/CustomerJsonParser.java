package json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CustomerJsonParser {

    public <T> T parse(String jsonMessage, Class<T> objectClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonMessage, objectClass);
    }
}
