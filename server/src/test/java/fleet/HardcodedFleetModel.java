package fleet;

import common.model.FleetModel;
import common.model.ShipModel;

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
