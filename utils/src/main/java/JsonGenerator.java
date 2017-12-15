import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGenerator {
    String createJson(Object object){

        ObjectMapper mapper = new ObjectMapper();
        //mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(object);
            //TODO: this.objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);

        } catch (JsonProcessingException e) {
            e.printStackTrace(); //TODO : exception handler
        }

        return jsonString;
    }
}