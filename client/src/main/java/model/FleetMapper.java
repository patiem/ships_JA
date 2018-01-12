package model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * It maps the received Fleet to a fleet model.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class FleetMapper {
  private FleetMapper() {
  }

  public static FleetModel mapToFleetModel(Fleet fleetToMap) {
    List<ShipModel> ships = fleetToMap.getShips()
        .stream()
        .map(ShipMapper::mapToModel)
        .collect(Collectors.toList());

    return new FleetModel(ships);
  }
}
