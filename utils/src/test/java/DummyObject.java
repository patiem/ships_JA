import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DummyObject {
    //class used only for Json testing

    private String name;
    private int age;
    private List<Integer> positions;

   @JsonCreator
    DummyObject(@JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("positions") List<Integer> positions) {
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
