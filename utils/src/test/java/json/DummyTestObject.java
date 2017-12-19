package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class is used for testing converting objects to Json.
 */
class DummyTestObject {

    private String name;
    private int age;
    private List<Integer> positions;

    @JsonCreator
    DummyTestObject(@JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("positions") List<Integer> positions) {
        this.name = name;
        this.age = age;
        this.positions = positions;
    }

    @JsonProperty("name")
    String getName() {
        return name;
    }

    @JsonProperty("age")
    int getAge() {
        return age;
    }

    @JsonProperty("positions")
    List<Integer> getPositions() {
        return positions;
    }
}