import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonGeneratorTest {

    @Test
    public void givenHardcodedFleetWhenCreateJSonThenReturnCorrectString() {
        JsonGenerator jsonGenerator = new JsonGenerator();
        String dummyObjectName = "some text";
        int dummyObjectAge = 25;

        List<Integer> dummyObjectPositions = Stream.iterate(1, n -> n + 1)
                .limit(10)
                .collect(Collectors.toList());

        DummyObject dummyObject = new DummyObject(dummyObjectName, dummyObjectAge, dummyObjectPositions);

        String nameJsonPart = "\"name\":\"" + dummyObjectName + "\"";
        String ageJsonPart = "\"age\":" + dummyObjectAge;
        String positionsJsonPart = "\"positions\":[1,2,3,4,5,6,7,8,9,10]";

        String actualJson = jsonGenerator.createJson(dummyObject);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualJson).contains(nameJsonPart);
        softly.assertThat(actualJson).contains(ageJsonPart);
        softly.assertThat(actualJson).contains(positionsJsonPart);
        softly.assertAll();
    }
}