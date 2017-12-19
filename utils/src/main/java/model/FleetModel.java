package model;

import java.util.ArrayList;
import java.util.List;

public class FleetModel {

    private final List<ShipModel> ships = new ArrayList<>();

    public FleetModel(List<ShipModel> ships){
        this.ships.addAll(ships);
    }

    public List<ShipModel> getShips() {
        return ships;
    }

}
