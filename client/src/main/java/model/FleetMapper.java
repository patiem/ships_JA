package model;

import java.util.ArrayList;
import java.util.List;

public class FleetMapper {

    public FleetModel mapToFleetModel(Fleet fleetToMap) {
        List<ShipModel> ships = new ArrayList<>();
        ShipMapper shipMapper = new ShipMapper();

        List<Ship> shipsToMap = fleetToMap.getShips();

        shipsToMap.forEach(ship -> ships.add(shipMapper.mapToModel(ship)));

        return new FleetModel(ships);
    }
}
