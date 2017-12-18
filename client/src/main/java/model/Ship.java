package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ship {

    private Mast[] masts;
    private int length;
    private int buildLength;


    public Ship(Mast mast) {
        length = mast.getLength();
        masts = new Mast[length];
        masts[0] = mast;
        buildLength = 1;
    }

    boolean isShipDone() {
        return buildLength == length;
    }

    void addMast(Mast mast) {
        if (!isShipDone()) masts[buildLength++] = mast;
    }

    public List<Position> getMastsPositions() {
        return Arrays.stream(masts).map(m -> m.position()).collect(Collectors.toList());
    }

    public Set<Position> getBoundPositions() {
        Set<Position> boundries = new HashSet<>();
        for (Mast mast : masts) {
            boundries.addAll(new BoundariesPosition(mast).countBoundariesForMast());
        }
        boundries.removeAll(getMastsPositions());
        return boundries;
    }
}
