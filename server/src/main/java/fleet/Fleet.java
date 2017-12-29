package fleet;

import java.util.List;

public interface Fleet {

  List<Integer> getFleetPositions();

  void hit(int position);

  List<Integer> getHitFields();

  default boolean isSunk() {
    return getHitFields().containsAll(getFleetPositions());
  }
}
