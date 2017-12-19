package fleet;

import model.ShipModel;
import java.util.List;

public interface Fleet {

    List<Integer> getFleetPositions();

    void hit(int position);

    List<Integer> getHitFields();

    int getSize();

    List<ShipModel> getShips();

    default boolean isSunk(){
        return getHitFields().containsAll(getFleetPositions());
    }
}
