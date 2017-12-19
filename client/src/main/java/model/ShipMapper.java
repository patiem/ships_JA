package model;

import java.util.ArrayList;
import java.util.List;

class ShipMapper {
    private static final int NUMBER_OF_COLUMNS = 10;

    ShipModel mapToModel(Ship ship) {
        List<Integer> fields = new ArrayList<>();

        Mast[] masts = ship.getMasts();
        for (Mast mast : masts) {
            Integer index = calculateIndexFromCoordinates(mast.getColumn(), mast.getRow());
            fields.add(index);
        }

        return new ShipModelImpl(fields);
    }

    private Integer calculateIndexFromCoordinates(int column, int row) {
        return row * NUMBER_OF_COLUMNS + column;
    }
}
