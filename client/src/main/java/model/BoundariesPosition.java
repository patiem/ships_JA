package model;

import java.util.ArrayList;
import java.util.List;

public class BoundariesPosition {

    private Mast mast;

    public BoundariesPosition(Mast mast) {
        this.mast = mast;
    }

    public List<Position> countBoundariesForMast() {

        List<Position> boundaries = new ArrayList<>();

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                int newX = (int) mast.getX() + x;
                int newY = (int) mast.getY() + y;
                if ( newX >= 0 && newX <= 9 && newY >= 0 && newY <= 9) {
                    boundaries.add(new Position(newX, newY));
                }
            }
        }
        return boundaries;
    }
}
