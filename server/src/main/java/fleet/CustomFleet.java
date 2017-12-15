package fleet;

import java.util.ArrayList;
import java.util.List;

public class CustomFleet implements Fleet {
    private List<Ship> ships;
    protected List<Integer> fleetPositions;
    private List<Integer> hitFields;

    public CustomFleet() {
        ships = new ArrayList<>();
        fleetPositions = new ArrayList<>();
        hitFields = new ArrayList<>();
    }

    public int getSize() {
        return ships.size();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Integer> getFleetPositions() {
        ships.stream()
                .forEach(ship -> fleetPositions.addAll(ship.getFields()));

        return fleetPositions;
    }

    public void hit(int position) {
    } //TODO: to be implemented

    public List<Integer> getHitFields() {
        return hitFields;
    }
}
