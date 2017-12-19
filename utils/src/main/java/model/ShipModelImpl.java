package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ShipModelImpl implements ShipModel{
    private final int numberOfMasts;
    private final List<Integer> fields = new ArrayList<>();

    @JsonCreator
    public ShipModelImpl(@JsonProperty("fields") List<Integer> fields){
        this.fields.addAll(fields);
        this.numberOfMasts = this.fields.size();

    }

    @JsonProperty("numberOfMasts")
    public int getNumberOfMasts(){
        return numberOfMasts;
    }

    @JsonProperty("fields")
    public List<Integer> getFields(){
        return fields;
    }
}
