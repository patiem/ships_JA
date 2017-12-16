package fleet;

import java.util.*;

public class HardcodedFleet implements Fleet {

    private static final Integer[] HARDCODED_POSITIONS = {0, 1, 2, 3, 17, 18, 19, 34, 40, 41, 44, 47, 54, 57, 61, 77, 78, 82, 85, 99};
    private final List<Integer> fleetPositions;
    private List<Integer> hitFields;
    private final List<Ship> ships;


    public HardcodedFleet() {
        ships = new ArrayList<>();
        fleetPositions = new ArrayList<>();
        hitFields = new ArrayList<>();
        fleetPositions.addAll(Arrays.asList(HARDCODED_POSITIONS));
    }

    @Override
    public List<Integer> getFleetPositions() {
        return fleetPositions;
    }

    @Override
    public void hit(int position) {
        hitFields.add(position);
    }

    @Override
    public List<Integer> getHitFields() {
        return hitFields;
    }

    @Override
    public int getSize() {
        return ships.size();
    }

    @Override
    public List<Ship> getShips() {
        return new ArrayList<>();
    }
}
