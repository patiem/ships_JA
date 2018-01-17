package fleet;

import model.FleetModel;
import model.ShipModel;

import java.util.List;

public class HardcodedFleetModel extends FleetModel {

  public HardcodedFleetModel(List<ShipModel> ships) {
    super(ships);
  }

  @Override
  public List<ShipModel> getShips() {
    return ships;
  }
}
