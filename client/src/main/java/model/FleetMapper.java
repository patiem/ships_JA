package model;

import java.util.ArrayList;
import java.util.List;

/**
 * It maps the received FleetCreator to a fleet model.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class FleetMapper {

  public FleetModel mapToFleetModel(Fleet fleet) {
    List<ShipModel> ships = new ArrayList<>();
    ShipMapper shipMapper = new ShipMapper();

    List<Ship> shipsToMap = fleet.getShips();
    shipsToMap.forEach(ship -> ships.add(shipMapper.mapToModel(ship)));

    return new FleetModel(ships);
  }
}
