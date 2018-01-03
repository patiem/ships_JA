package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.FleetModel;

/**
 * It represents the initial message sent from GUI to the server.
 * @author Emilia Ciastek/Bartosz Pieczara
 * @version 1.5
 */
public class InitMessage {


  private String name;
  private FleetModel fleetModel;

  @JsonCreator
  public InitMessage(@JsonProperty("name") String name, @JsonProperty("fleetModel") FleetModel fleetModel) {
    this.name = name;
    this.fleetModel = fleetModel;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("fleetModel")
  public FleetModel getFleetModel() {
    return fleetModel;
  }


}
