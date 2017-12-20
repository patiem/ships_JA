package fleet;

import model.FleetModel;
import model.ShipModel;

import java.util.ArrayList;
import java.util.List;

public class CustomFleet implements Fleet {
    private final List<ShipModel> ships;
    private final List<Integer> fleetPositions;
    private final List<Integer> hitFields;

    public CustomFleet() {
        ships = new ArrayList<>();
        fleetPositions = new ArrayList<>();
        hitFields = new ArrayList<>();
    }


    public CustomFleet(FleetModel fleetModel) {
        this.ships = new ArrayList<>(fleetModel.getShips());
        fleetPositions = new ArrayList<>();
        hitFields = new ArrayList<>();

    }

    public int getSize() {
        return ships.size();
    }

    public void addShip(ShipModel ship) {
        ships.add(ship);
    }

    public List<ShipModel> getShips() {
        return ships;
    }

    public List<Integer> getFleetPositions() {
        ships.forEach(ship -> fleetPositions.addAll(ship.getFields()));

        return fleetPositions;
    }

    public void hit(int position) {
        hitFields.add(position);
    }

    public List<Integer> getHitFields() {
        return hitFields;
    }
}
