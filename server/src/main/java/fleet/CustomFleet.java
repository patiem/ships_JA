package fleet;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class CustomFleet implements Fleet {
    private final List<Ship> ships;
    private final List<Integer> fleetPositions;
    private final List<Integer> hitFields;

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
        ships.forEach(ship -> fleetPositions.addAll(ship.getFields()));

        return fleetPositions;
    }

    public void hit(int position) {
        throw new NotImplementedException();
    }

    public List<Integer> getHitFields() {
        return hitFields;
    }
}
