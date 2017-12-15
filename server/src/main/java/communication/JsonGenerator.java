package communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGenerator {
    String createJson(Object object){

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            e.printStackTrace(); //TODO : exception handler
        }
        return jsonString;
    }
}