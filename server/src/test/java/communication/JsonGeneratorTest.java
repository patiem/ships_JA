package communication;

import fleet.Fleet;
import fleet.HardcodedFleet;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonGeneratorTest {
    @Test
    public void givenHardcodedFleetWhenCreateJSonThenReturnCorrectString() {
        JsonGenerator jsonGenerator = new JsonGenerator();
        Fleet fleet = new HardcodedFleet();
        String shipsJsonPart = "\"ships\":[]";
        String fleetPositionsJsonPart = "\"fleetPositions\":[0,1,2,3,17,18,19,34,40,41,44,47,54,57,61,77,78,82,85,99]";
        String hitFieldsJsonPart = "\"hitFields\":[]";
        String sizeJsonPart = "\"size\":0";

        String actualJson = jsonGenerator.createJson(fleet);

        assertThat(actualJson.contains(shipsJsonPart));
        assertThat(actualJson.contains(fleetPositionsJsonPart));
        assertThat(actualJson.contains(hitFieldsJsonPart));
        assertThat(actualJson.contains(sizeJsonPart));
    }

}