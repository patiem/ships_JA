import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {

    public <T> T parse(String jsonMessage, Class<T> dummyObjectClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonMessage, dummyObjectClass);
    }
}
