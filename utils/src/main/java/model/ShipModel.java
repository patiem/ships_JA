package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * It represents each of the ships that a fleet consists of.
 *
 * @author Emilia Ciastek
 * @version 1.5
 */
public class ShipModel {
  private final int numberOfMasts;
  private final List<Integer> fields = new ArrayList<>();

  @JsonCreator
  public ShipModel(@JsonProperty("fields") List<Integer> fields) {
    this.fields.addAll(fields);
    this.numberOfMasts = this.fields.size();
  }

  @JsonProperty("numberOfMasts")
  public int getNumberOfMasts() {
    return numberOfMasts;
  }

  @JsonProperty("fields")
  public List<Integer> getFields() {
    return fields;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    fields.forEach(position -> builder.append(String.valueOf(position)).append(","));

    return builder.toString();
  }
}
