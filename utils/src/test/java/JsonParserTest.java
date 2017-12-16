import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonParserTest {

    @Test
    public void shouldReturnParsedObjectFromJson() throws IOException {
        JsonParser jsonParser = new JsonParser();
        String jsonMessage = "{\"name\":\"name value\",\"age\":156,\"positions\":[1,2,3,4,5,6,7,8,9,10]}";
        String expectedName = "name value";
        int expectedAge = 156;
        List<Integer> expectedPositions = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        DummyTestObject dummyTestObject = jsonParser.parse(jsonMessage, DummyTestObject.class);

        String actualName = dummyTestObject.getName();
        int actualAge = dummyTestObject.getAge();
        List<Integer> actualPositions = dummyTestObject.getPositions();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualName).isEqualTo(expectedName);
        softly.assertThat(actualAge).isEqualTo(expectedAge);
        softly.assertThat(actualPositions).containsAll(expectedPositions);
        softly.assertAll();
    }
}