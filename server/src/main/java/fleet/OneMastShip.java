package fleet;

import java.util.ArrayList;
import java.util.List;

class OneMastShip implements Ship {
    private static final int NUMBER_OF_MASTS = 1;
    private List<Integer> fields;

    OneMastShip(List<Integer> positions) {
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
