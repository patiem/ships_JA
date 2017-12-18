package fleet;

import java.util.List;

public interface Fleet {

    List<Integer> getFleetPositions();

    void hit(int position);

    List<Integer> getHitFields();

    int getSize();

    List<Ship> getShips();
}
