package model;

import java.util.ArrayList;
import java.util.List;

public class ShipModelImpl implements ShipModel{
    private final int numberOfMasts;
    private final List<Integer> fields = new ArrayList<>();

    public ShipModelImpl(List<Integer> fields){
        this.fields.addAll(fields);
        this.numberOfMasts = this.fields.size();

    }

    public int getNumberOfMasts(){
        return numberOfMasts;
    }

    public List<Integer> getFields(){
        return fields;
    }
}
