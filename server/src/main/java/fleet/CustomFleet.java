package fleet;

import java.util.ArrayList;
import java.util.List;
import model.FleetModel;
import model.ShipModel;

/**
 * It holds information on player's fleet.
 * @author Emilia Ciastek
 * @version 1.5
 */
public class CustomFleet implements Fleet {
  private final List<ShipModel> ships;
  private final List<Integer> fleetPositions;
  private final List<Integer> hitFields;

  public CustomFleet(final FleetModel fleetModel) {
    this.ships = new ArrayList<>(fleetModel.getShips());
    fleetPositions = new ArrayList<>();
    hitFields = new ArrayList<>();

  }

  public List<Integer> getFleetPositions() {
    ships.forEach(ship -> fleetPositions.addAll(ship.getFields()));

    return fleetPositions;
  }

  public void hit(final int position) {
    hitFields.add(position);
  }

  public List<Integer> getHitFields() {
    return hitFields;
  }
}
