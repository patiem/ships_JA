package fleet;

import java.util.List;

/**
 * It exposes methods necessary for adding and retrieving information on a player's fleet.
 * @author Emilia Ciastek
 * @version 1.5
 */
public interface Fleet {

  List<Integer> getFleetPositions();

  void hit(int position);

  List<Integer> getHitFields();

  default boolean isSunk() {
    return getHitFields().containsAll(getFleetPositions());
  }
}
