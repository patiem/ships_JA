package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ShotResponse implements Message {
    private String header;
    private List<Integer> shots;

    @JsonCreator
    public ShotResponse(@JsonProperty("header") String header, @JsonProperty("shots") List<Integer> shots) {
        this.header = header;
        this.shots = shots;
    }

    @JsonProperty("header")
    @Override
    public String getHeader() {
        return header;
    }

    @JsonProperty("shots")
    public List<Integer> getShots() {
        return shots;
    }
}
