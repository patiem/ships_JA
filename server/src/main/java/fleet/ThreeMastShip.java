package fleet;

import java.util.ArrayList;
import java.util.List;

public class ThreeMastShip implements Ship {
    private final List<Integer> fields;
    private static final int NUMBER_OF_MASTS = 3;

    public ThreeMastShip(List<Integer> positions) {
        fields = new ArrayList<>();
        fields.addAll(positions);
    }

    @Override
    public int getNumberOfMasts() {
        return NUMBER_OF_MASTS;
    }

    @Override
    public List<Integer> getFields() {
        return fields;
    }
}
